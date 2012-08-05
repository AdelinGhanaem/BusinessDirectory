package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.User;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface UserRepository {

  public void add(String username, String password);

  public User getUser(String email, String password);

  boolean isEmailReserved(String email);

  Long getUserId(String username, String password);

  User getById(Long ownerId);
}
