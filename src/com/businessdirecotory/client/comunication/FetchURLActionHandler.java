package com.businessdirecotory.client.comunication;

import com.businessdirecotory.shared.entites.actions.FetchURLAction;
import com.businessdirecotory.shared.entites.reponses.FetchUrlResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchURLActionHandler implements ActionHandler<FetchURLAction, FetchUrlResponse> {
  @Override
  public FetchUrlResponse handle(FetchURLAction action) {
    return new FetchUrlResponse(BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/upload"));
  }
}
