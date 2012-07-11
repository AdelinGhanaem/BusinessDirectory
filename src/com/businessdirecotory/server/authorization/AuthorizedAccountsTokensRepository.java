package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.client.authorization.Token;

import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface AuthorizedAccountsTokensRepository {

  /**
   * Adds a token to the Authorized Repository
   *
   * @param token
   */
  void add(Token token);

  /**
   * deletes a token from repository  in response to user logout action
   *
   * @param token
   */
  void delete(Token token);
}
