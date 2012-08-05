package com.businessdirecotory.server.companyprofile;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ImagesRepository {


  private DatastoreService service;

  @Inject
  public ImagesRepository(DatastoreService service) {

    this.service = service;
  }

  public ImageData getImage(long userId) {
    Query query = new Query(ImageEntity.KIND);
    query.setFilter(new Query.FilterPredicate(ImageEntity.OWNER_ID, Query.FilterOperator.EQUAL, userId));
    Entity entity = service.prepare(query).asSingleEntity();
    if (entity != null) {
      return new ImageData((String) entity.getProperty(ImageEntity.URL));
    }
    return null;
  }


  public void saveImage(long userID, ImageData imageData) {
    Entity entity = new Entity(ImageEntity.KIND);
    entity.setProperty(ImageEntity.URL, imageData.getUrl());
    entity.setProperty(ImageEntity.OWNER_ID, userID);
    service.put(entity);
  }

  public String getURL(long userId) {
    Query query = new Query(ImageEntity.KIND);
    query.setFilter(new Query.FilterPredicate(ImageEntity.OWNER_ID, Query.FilterOperator.EQUAL, userId));
    Entity entity = service.prepare(query).asSingleEntity();
    return (String) entity.getProperty(ImageEntity.URL);
  }
}
