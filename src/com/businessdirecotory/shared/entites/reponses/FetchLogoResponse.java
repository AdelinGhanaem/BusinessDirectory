package com.businessdirecotory.shared.entites.reponses;

import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchLogoResponse implements Response {

  private String imageURL;

  public FetchLogoResponse(String imageURL) {

    this.imageURL = imageURL;
  }

  public FetchLogoResponse() {
  }

  public String getImageURL() {
    return imageURL;
  }

}
