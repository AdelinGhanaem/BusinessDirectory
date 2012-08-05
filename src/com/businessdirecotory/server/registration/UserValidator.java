package com.businessdirecotory.server.registration;


import com.businessdirecotory.shared.entites.User;

import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface UserValidator {
  public List<String> validate(User user);
}