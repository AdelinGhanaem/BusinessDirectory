package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.client.authorization.Token;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CheckAuthorizationResponse implements Response {




  public CheckAuthorizationResponse(Token token) {
    this.token = token;
  }

  private Token token;



  public CheckAuthorizationResponse() {
  }



  public Token getToken() {
    return token;
  }
}
