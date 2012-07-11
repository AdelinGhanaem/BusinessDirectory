package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizedAccountsTokensRepositoryImpl implements AuthorizedAccountsTokensRepository {

  private DatastoreService service;

  @Inject
  public AuthorizedAccountsTokensRepositoryImpl(DatastoreService service) {

    this.service = service;
  }

  @Override
  public void add(Token token) {
    Entity entity = new Entity("Token");
    entity.setProperty("expireDate", token.getExpireDate());
    entity.setProperty("username", token.getUser());
    service.put(entity);
  }

  @Override
  public void delete(Token token) {
    Query query = new Query("Token");
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, token.getUser()));
    Entity entity = service.prepare(query).asSingleEntity();
    service.delete(entity.getKey());
  }
}
