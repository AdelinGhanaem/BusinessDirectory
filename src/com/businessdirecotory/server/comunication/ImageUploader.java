package com.businessdirecotory.server.comunication;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepository;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepositoryImpl;
import com.businessdirecotory.server.companyprofile.ImageData;
import com.businessdirecotory.server.companyprofile.ImagesRepository;
import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.CompaniesRepositoryImpl;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.server.registration.UserRepositoryImpl;
import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.images.Transform;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@Singleton
public class ImageUploader extends HttpServlet {


  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  private AuthorizedTokensRepository tokensRepository = new AuthorizedTokensRepositoryImpl(service);

  private ImagesRepository imagesRepository = new ImagesRepository(service);

  private CompaniesRepository companiesRepository = new CompaniesRepositoryImpl(new AnnotationObjectDatastore());

  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  private UserRepository userRepository = new UserRepositoryImpl(service);

  private ImagesService imagesService = ImagesServiceFactory.getImagesService();


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.getCookies();

    String userIdString = request.getParameter("userId");

    String tokenIdString = request.getParameter("tokenId");

    String username = request.getParameter("username");

    Long userId = new Long(userIdString);

    Long tokenId = new Long(tokenIdString);

    Token token = new Token(tokenId, userId, username, new Date());
    if (tokensRepository.isAuthorized(token, token.getExpireDate())) {

      Map<String, List<BlobKey>> blobKeyMap = blobstoreService.getUploads(request);

      List<BlobKey> blobKeys = blobKeyMap.get("logoImage");
      if (blobKeys == null) {
        System.out.println("somethingWentWrong !!");
      } else {
        BlobKey blobKey = blobKeys.get(0);
        String imageURL = resizeImage(blobKey);
        saveImageInDataStore(userId, imageURL);
      }
    }
  }

  private String resizeImage(BlobKey blobKey) {
    Image image = ImagesServiceFactory.makeImageFromBlob(blobKey);
    Transform transform = ImagesServiceFactory.makeResize(120, 120);
    Image newImage = imagesService.applyTransform(transform, image);
    image.setImageData(newImage.getImageData());
    return imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey));
  }

  private void saveImageInDataStore(Long userId, String url) {
    imagesRepository.saveImage(userId, new ImageData(url));
//    User user = userRepository.getById(userId);
    Company company = companiesRepository.getByUserId(userId);
    company.setLogoURL(url);
    companiesRepository.add(company);
  }

  private String getImageURL(BlobKey blobKey) {
    return imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey));
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("OK !");
  }
}
