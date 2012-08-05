package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.User;
import com.google.inject.Inject;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserValidatorImpl implements UserValidator {


  private Validator validator;

  @Inject
  public UserValidatorImpl(Validator validator) {
    this.validator = validator;
  }

  @Override
  public List<String> validate(final User user) {
    Set<ConstraintViolation<User>> set = validator.validate(user);
    List<String> errors = new ArrayList<String>();
    for (ConstraintViolation<User> violation : set) {
      errors.add(violation.getMessage());
    }
    return errors;
  }
}
