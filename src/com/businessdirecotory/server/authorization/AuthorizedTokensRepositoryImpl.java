package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizedTokensRepositoryImpl implements AuthorizedTokensRepository {

  private DatastoreService service;

  @Inject
  public AuthorizedTokensRepositoryImpl(DatastoreService service) {

    this.service = service;
  }

  @Override
  public void add(Token token, Date expireDate) {

    Query query = new Query("Token");
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, token.getUser()));
    Entity entity = service.prepare(query).asSingleEntity();
    if (entity != null) {
      Key key = KeyFactory.createKey(entity.getKind(), entity.getKey().getId());
      try {
        Entity returnedEntity = service.get(key);
        returnedEntity.setProperty("expireDate", expireDate);
        service.put(returnedEntity);
      } catch (EntityNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      Entity newEntity = new Entity("Token");
      newEntity.setProperty("expireDate", expireDate);
      newEntity.setProperty("username", token.getUser());
      service.put(newEntity);
    }

  }

  @Override
  public void delete(Token token) {
    Query query = new Query("Token");
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, token.getUser()));
    Entity entity = service.prepare(query).asSingleEntity();
    service.delete(entity.getKey());
  }

  @Override
  public boolean isAuthorized(Token token, Date currentDate) {

    Query query = new Query("Token");
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, token.getUser()));
    Entity tokenEntity = service.prepare(query).asSingleEntity();
    if (tokenEntity == null) {
      return false;
    } else {
      Date expiredDate = (Date) tokenEntity.getProperty("expireDate");
      return expiredDate.after(currentDate) || expiredDate.equals(currentDate);
    }
  }


}
