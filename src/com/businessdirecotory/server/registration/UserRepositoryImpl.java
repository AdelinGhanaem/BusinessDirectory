package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRepositoryImpl implements UserRepository {

  private DatastoreService service;

  @Inject
  public UserRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  public void add(User user) {
    Entity entity = new Entity(UserEntity.KIND);
    entity.setProperty(UserEntity.USERNAME, user.getUsername());
    entity.setProperty(UserEntity.PASSWORD, user.getPassword());
    entity.setProperty(UserEntity.COMPANY_ID, user.getCompanyId());
    service.put(entity);
  }

  @Override
  public void add(String username, String password) {
    Entity entity = new Entity(UserEntity.KIND);
    entity.setProperty(UserEntity.USERNAME, username);
    entity.setProperty(UserEntity.PASSWORD, password);
    service.put(entity);
  }

  public User getUser(String email, String password) {
    Query query = new Query(UserEntity.KIND);
    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate(UserEntity.USERNAME, Query.FilterOperator.EQUAL, email)
            , new Query.FilterPredicate(UserEntity.PASSWORD, Query.FilterOperator.EQUAL, password)));
    Entity entity = service.prepare(query).asSingleEntity();
    return createUser(entity);
  }

  private User createUser(Entity entity) {
    return new User((String) entity.getProperty(UserEntity.USERNAME), (String) entity.getProperty(UserEntity.PASSWORD));
  }

  @Override
  public boolean isEmailReserved(String email) {
    Query query = new Query(UserEntity.KIND);
    query.setFilter(new Query.FilterPredicate(UserEntity.USERNAME, Query.FilterOperator.EQUAL, email));
    return service.prepare(query).asSingleEntity() != null;
  }

  @Override
  public Long getUserId(String username, String password) {
    Query query = new Query(UserEntity.KIND);
    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate(UserEntity.USERNAME, Query.FilterOperator.EQUAL, username),
            new Query.FilterPredicate(UserEntity.PASSWORD, Query.FilterOperator.EQUAL, password)));

    Entity entity = service.prepare(query).asSingleEntity();
    if (entity != null) {
      return entity.getKey().getId();
    }
    return null;
  }

  @Override
  public User getById(Long ownerId) {
    Key key = KeyFactory.createKey(UserEntity.KIND, ownerId);
    Entity entity = null;
    try {
      entity = service.get(key);
      return createUser(entity);
    } catch (EntityNotFoundException e) {
      throw new UserNotFoundException();
    }
  }
}

