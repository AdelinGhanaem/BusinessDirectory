package com.businessdirecotory.client.authorization;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Token implements Serializable {


  private String user;

  private Date expireDate;

  private Long tokenId;

  private Long userId;

  public Token() {
  }



  public Token(Long tokenId, Long userId, String username,Date expireDate) {
    this.tokenId = tokenId;
    this.userId = userId;
    user = username;
    this.expireDate = expireDate;
  }


  public Date getExpireDate() {
    return expireDate;
  }

  public String getUser() {
    return user;
  }

  public Long getTokenId() {
    return tokenId;
  }

  public Long getUserId() {
    return userId;
  }
}
