package com.businessdirecotory.server.registration;

import com.businessdirecotory.AppEngineTestCase;
import com.businessdirecotory.shared.entites.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRepositoryImplTest extends AppEngineTestCase {

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  UserRepositoryImpl repository = new UserRepositoryImpl(service);

  public UserRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
  }

  @Test
  public void addsUserToRepository() {

    User user = new User(0l, "email", "password");

    repository.add(user);

    User returnedUser = repository.getUser("email", "password");

    assertThat(returnedUser, is(notNullValue()));

    assertThat(returnedUser.getUsername(), is(equalTo("email")));

    assertThat(returnedUser.getPassword(), is(equalTo("password")));
  }

  @Test(expected = UserNotFoundException.class)
  public void throwsUserNotFoundExceptionIfUserIdNotFound() {
    User returnedUser = repository.getById(2l);
  }

  @Test
  public void returnsTrueIfEmailIsReserved() {
    User user = new User(0l, "mail@mail.com", "password");
    repository.add(user);
    boolean isReserved = repository.isEmailReserved("mail@mail.com");
    assertThat(isReserved, is(true));
  }

  @Test
  public void returnsFalseWhenEmailIsNotReserved() {
    boolean isReserved = repository.isEmailReserved("mail@mail.com");
    assertThat(isReserved, is(false));
  }

  @Test
  public void returnsUserIdIfUsernameAndPasswordAreRegistered() {
    User user = new User("username", "password");
    repository.add(user);
    Long userId = repository.getUserId("username", "password");
    assertThat(userId, is(notNullValue()));
  }

  @Test
  public void returnsNullIfUsernameAndPasswordAreNotRegistered() {
    Long userID = repository.getUserId("username", "password");
    assertThat(userID, is(equalTo(null)));
  }


  @Test
  public void returnsUserByItsId() throws Exception {
    Entity entity = new Entity(UserEntity.KIND);
    entity.setProperty(UserEntity.USERNAME, "username");
    entity.setProperty(UserEntity.PASSWORD, "password");
    service.put(entity);
    User user = repository.getById(entity.getKey().getId());
    assertThat(user, is(notNullValue()));
    assertThat(user.getUsername(), is(equalTo("username")));
    assertThat(user.getPassword(), is(equalTo("password")));

  }
}
