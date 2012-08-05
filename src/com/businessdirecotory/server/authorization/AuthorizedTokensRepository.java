package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;

import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface AuthorizedTokensRepository {

  /**
   * Adds a token to the Authorized Repository
   *
   * @param token
   * @param expireDate
   */
  void add(Token token, Date expireDate);

  /**
   * deletes a token from companiesRepository  in response to user logout action
   *
   * @param token
   */
  void delete(Token token);

  /**
   *
   * @param token
   * @param currentDate
   * @return
   */
  boolean isAuthorized(Token token, Date currentDate);


}
