package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationAction implements Action<AuthorizationResponse> {

  private User user;

  public AuthorizationAction(User user) {

    this.user = user;
  }

  public AuthorizationAction() {
  }

  public User getUser() {
    return user;
  }
}
