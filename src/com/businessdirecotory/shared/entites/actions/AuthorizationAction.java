package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationAction implements Action<AuthorizationResponse> {

  private Account account;

  public AuthorizationAction(Account account) {

    this.account = account;
  }

  public AuthorizationAction() {
  }

  public Account getAccount() {
    return account;
  }

}
