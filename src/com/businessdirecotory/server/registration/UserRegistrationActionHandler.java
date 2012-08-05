package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.actions.UserRegistrationAction;
import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationActionHandler implements ActionHandler<UserRegistrationAction, UserRegistrationResponse> {


  private UserRepository userRepository;

  private UserValidator validator;


  @Inject
  public UserRegistrationActionHandler(UserRepository userRepository,
                                       UserValidator validator) {
    this.userRepository = userRepository;
    this.validator = validator;
  }


  @Override
  public UserRegistrationResponse handle(UserRegistrationAction action) {
    User user = action.getUser();
    List<String> errors = validator.validate(user);
    if (errors.size() > 0) {
      return new UserRegistrationResponse(new ArrayList<String>(errors));
    } else {
      if (userRepository.isEmailReserved(action.getUser().getUsername())) {
        errors.add("e-mail " + action.getUser().getUsername() + "е вече резервиран, моля въведете друг e-mail");
      } else {
        userRepository.add(user.getUsername(), user.getPassword());
      }
      return new UserRegistrationResponse(new ArrayList<String>(errors));
    }

  }
}
