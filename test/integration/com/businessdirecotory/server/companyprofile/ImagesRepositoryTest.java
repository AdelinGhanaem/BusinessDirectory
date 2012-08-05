package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.AppEngineTestCase;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ImagesRepositoryTest extends AppEngineTestCase {

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();


  public ImagesRepositoryTest() {
    super(new LocalDatastoreServiceTestConfig());
  }

  ImagesRepository repository = new ImagesRepository(service);

  @Test
  public void returnsImageByUserId() throws Exception {

    repository.saveImage(1l, new ImageData("URL"));

    ImageData imageData = repository.getImage(1l);

    assertThat(imageData, is(notNullValue()));

    assertThat(imageData.getUrl(), is(notNullValue()));

  }


  @Test
  public void getAnotherImage() {

    String imageURL = "url2";

    repository.saveImage(1l, new ImageData(imageURL));

    ImageData imageData = repository.getImage(1l);

    assertThat(imageData, is(notNullValue()));

    assertThat(imageData.getUrl(), is(notNullValue()));

    assertThat(imageData.getUrl(), is(equalTo(imageURL)));
  }

  @Test
  public void shouldReturnImageURL() {

    ImageData imageData = new ImageData("url");

    repository.saveImage(1l, imageData);

    String string = repository.getURL(1l);

    assertThat(string, is(notNullValue()));

    assertThat(string, is(equalTo("url")));

  }

}
