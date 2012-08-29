package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.shared.entites.reponses.CheckAuthorizationResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CheckAuthorizationAction implements Action<CheckAuthorizationResponse> {

  private Token token;

  public CheckAuthorizationAction(Token token) {

    this.token = token;
  }

  public CheckAuthorizationAction() {
  }

  public Token getToken() {
    return token;
  }
}
