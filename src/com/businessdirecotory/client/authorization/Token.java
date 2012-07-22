package com.businessdirecotory.client.authorization;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Token implements Serializable {

  private String user;

  private Date expireDate;

  public Token() {
  }

  public Token(String user, Date expireDate) {
    this.user = user;
    this.expireDate = expireDate;
  }

  public Token(String user) {
    this.user = user;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Date getDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }
}
