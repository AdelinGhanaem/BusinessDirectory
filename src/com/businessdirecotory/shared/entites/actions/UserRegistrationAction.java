package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationAction implements Action<UserRegistrationResponse> {


  private User user;


  public UserRegistrationAction() {

  }

  public UserRegistrationAction(User user) {

    this.user = user;
  }



  public User getUser() {
    return user;
  }

}

