package com.businessdirecotory.client.authorization;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface SecurityTokenProvider {


  public void setToken(Token token);


  public Token getToken();
}
