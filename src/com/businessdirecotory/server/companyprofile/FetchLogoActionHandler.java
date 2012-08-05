package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.actions.FetchLogoAction;
import com.businessdirecotory.shared.entites.reponses.FetchLogoResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchLogoActionHandler implements ActionHandler<FetchLogoAction, FetchLogoResponse> {


  private ImagesRepository imagesRepository;

  @Inject
  public FetchLogoActionHandler(ImagesRepository imagesRepository) {
    this.imagesRepository = imagesRepository;
  }

  @Override
  public FetchLogoResponse handle(FetchLogoAction action) {
    String imageURL = imagesRepository.getURL(action.getUserId());
    return new FetchLogoResponse(imageURL);
  }
}
