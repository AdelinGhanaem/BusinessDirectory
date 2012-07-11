package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.shared.entites.reponses.LogoutResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class LogoutAction implements Action<LogoutResponse> {


  private Token token;

  public LogoutAction(Token token) {

    this.token = token;
  }

  public Token getToken() {
    return token;
  }

  public LogoutAction() {
  }
}
