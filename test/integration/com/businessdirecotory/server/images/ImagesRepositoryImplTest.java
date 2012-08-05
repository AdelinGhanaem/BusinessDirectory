package com.businessdirecotory.server.images;

import com.businessdirecotory.AppEngineTestCase;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ImagesRepositoryImplTest extends AppEngineTestCase {


  public ImagesRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
  }


  @Test
  public void savesImageAndReturnsImageId() throws Exception {
    BlobKey blobKey = new BlobKey("blob");
  }
}
