package com.businessdirecotory.client.authorization;

import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SecuredAction<T extends Response> implements Action<SecuredResponse> {


  private Action<? extends Response> action;

  private Token token;

  public SecuredAction() {
  }

  public SecuredAction(Action<? extends Response> action, Token token) {
    this.action = action;
    this.token = token;
  }



  public Action<? extends Response> getAction() {
    return action;
  }



  public Token getToken() {
    return token;
  }
}
