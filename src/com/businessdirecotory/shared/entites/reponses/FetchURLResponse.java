package com.businessdirecotory.shared.entites.reponses;

import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchURLResponse implements Response {
  private String url;

  public FetchURLResponse(String url) {

    this.url = url;
  }

  public FetchURLResponse() {
  }

  public String getUrl() {
    return url;
  }
}
