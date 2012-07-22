package com.businessdirecotory.server.authorization;

import com.businessdirecotory.AppEngineTestCase;
import com.businessdirecotory.client.authorization.Token;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizedTokensRepositoryImplTest extends AppEngineTestCase {


  private DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  private AuthorizedTokensRepositoryImpl repository;

  public AuthorizedTokensRepositoryImplTest() {

    super(new LocalDatastoreServiceTestConfig());

    repository = new AuthorizedTokensRepositoryImpl(service);
  }


  @Test
  public void addTokenToRepository() {
    String user = "user";
    Token token = new Token(user);
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, 3);
    Date expireDate = calendar.getTime();
    repository.add(token, expireDate);
    Query query = new Query("Token");
    query.setFilter(Query.CompositeFilterOperator.or(
            new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, token.getUser()),
            new Query.FilterPredicate("expireDate", Query.FilterOperator.EQUAL, new Date())));
    Entity entity = service.prepare(query).asSingleEntity();
    assertThat(entity, is(notNullValue()));
    assertThat((String) entity.getProperty("username"), is(equalTo(user)));
    assertEquals(entity.getProperty("expireDate"), expireDate);
  }

  @Test
  public void calendarTest() {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, 3);
    Date expireDate = calendar.getTime();

    assertThat(date.after(expireDate), is(equalTo(false)));

    System.out.println(date);

    System.out.println(expireDate);

    System.out.println(date.after(expireDate));

  }

  @Test
  public void deletesTokenFromRepository() {
    Token token = new Token("user");
    repository.add(token, new Date());
    repository.delete(token);
    Query query = new Query("Token");
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, "user"));
    Entity entity = service.prepare(query).asSingleEntity();
    assertThat(entity, is(nullValue()));
  }


  @Test
  public void returnsTrueWhenTokenIsAvailableAndNotExpired() {
    Token token = new Token();
    Calendar calendar = Calendar.getInstance();
    Date currentDate = calendar.getTime();
    calendar.add(Calendar.DAY_OF_MONTH, 3);
    Date expiredDate = calendar.getTime();
    repository.add(token, expiredDate);
    Boolean isAuthorized = repository.isAuthorized(token, currentDate);
    assertThat(isAuthorized, is(true));

  }

  @Test
  public void returnsFalseWhenTokenIsAvailableButDateIsExpired() {
    Token token = new Token();
    Calendar calendar = Calendar.getInstance();
    Date currentDate = calendar.getTime();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    Date expiredDate = calendar.getTime();
    repository.add(token, expiredDate);
    Boolean isAuthorized = repository.isAuthorized(token, currentDate);
    assertThat(isAuthorized, is(false));
  }

  @Test
  public void returnsFalseWhenTokeIsNotAvailable() {
    boolean isAuthorized = repository.isAuthorized(new Token(), new Date());
    assertThat(isAuthorized, is(false));
  }


  @Test
  public void tokenIsAlteredWhenTokenWithSameUsernameExists() {
    Token token = new Token("username");
    Date date = new Date();
    repository.add(token, date);
    repository.add(new Token("username"), date);
    Boolean isAuthorized = repository.isAuthorized(token, date);
    assertThat(isAuthorized, is(equalTo(true)));
  }




}
