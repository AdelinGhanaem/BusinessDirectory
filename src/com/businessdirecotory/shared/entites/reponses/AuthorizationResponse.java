package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.client.authorization.Token;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationResponse implements Response {

  private Token token;

  public AuthorizationResponse(Token token) {

    this.token = token;
  }

  public AuthorizationResponse() {
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }

}
