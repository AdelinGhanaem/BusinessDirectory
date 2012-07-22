package com.businessdirecotory.shared.entites.reponses;

import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchUrlResponse implements Response {
  private String url;

  public FetchUrlResponse(String url) {

    this.url = url;
  }

  public FetchUrlResponse() {
  }

  public String getUrl() {
    return url;
  }
}
