package com.businessdirecotory.client.authorization;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Account implements Serializable {


  private String username;

  private String password;

  public Account() {
  }

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
