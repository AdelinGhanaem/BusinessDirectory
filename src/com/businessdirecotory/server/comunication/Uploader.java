package com.businessdirecotory.server.comunication;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepository;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepositoryImpl;
import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.server.companyregistration.CompaniesRepositoryImpl;
import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.inject.Inject;
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
public class Uploader extends HttpServlet {


  DatastoreService service = DatastoreServiceFactory.getDatastoreService();
  @Inject
  private AuthorizedTokensRepository tokensRepository = new AuthorizedTokensRepositoryImpl(service);

  @Inject
  CompaniesRepository repository = new CompaniesRepositoryImpl(service);

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    if (tokensRepository.isAuthorized(new Token(username), new Date())) {
      BlobstoreService service = BlobstoreServiceFactory.getBlobstoreService();
      Map<String, List<BlobKey>> blobKeyMap = service.getUploads(request);
      List<BlobKey> blobKeys = blobKeyMap.get("logoImage");
      if (blobKeys == null) {
        System.out.println("somethingWentWrong !!");
      } else {
        BlobKey blobKey = blobKeys.get(0);
//        blobKey.getKeyString();
        Company company = repository.getByEmail(username);
//        company.setLogKeyString();
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        System.out.println(imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey)));
//        BlobstoreServiceFactory.getBlobstoreService().serve(blobKey, response);
      }
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("OK !");
  }
}
