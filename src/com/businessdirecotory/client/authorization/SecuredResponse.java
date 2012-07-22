package com.businessdirecotory.client.authorization;

import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SecuredResponse<R extends Response> implements Response {

  private R response;

  public SecuredResponse() {
  }

  public SecuredResponse(R response) {

    this.response = response;
  }

  public R getResponse() {
    return response;
  }
}
