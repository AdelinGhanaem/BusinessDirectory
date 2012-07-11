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
  }

  @Override
  public Token getToken() {
    return new Token(Cookies.getCookie("username"));
  }

}
