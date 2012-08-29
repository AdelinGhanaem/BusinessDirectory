package com.businessdirecotory.client.authorization;

import com.google.gwt.user.client.Cookies;

import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SecurityTokenProviderImpl implements SecurityTokenProvider {
  @Override
  public void setToken(Token token) {

    Cookies.setCookie("username", token.getUser(), token.getExpireDate());

    Cookies.setCookie("userId", String.valueOf(token.getUserId()), token.getExpireDate());

    Cookies.setCookie("sid", String.valueOf(token.getTokenId()), token.getExpireDate());
  }

  @Override
  public Token getToken() {
    String username = Cookies.getCookie("username");
    String tokenIdString = Cookies.getCookie("sid");
    String userIdString = Cookies.getCookie("userId");

    Long tokenId = new Long(tokenIdString);
    Long userId = new Long(userIdString);
    //TODO:try to find some better way to accomplish this ... !
    Date date = new Date();

    return new Token(tokenId, userId, username, date);
  }

}
