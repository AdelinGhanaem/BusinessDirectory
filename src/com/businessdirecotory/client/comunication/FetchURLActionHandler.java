package com.businessdirecotory.client.comunication;

import com.businessdirecotory.shared.entites.actions.FetchURLAction;
import com.businessdirecotory.shared.entites.reponses.FetchURLResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchURLActionHandler implements ActionHandler<FetchURLAction, FetchURLResponse> {
  @Override
  public FetchURLResponse handle(FetchURLAction action) {
    return new FetchURLResponse(BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/upload"));
  }
}
