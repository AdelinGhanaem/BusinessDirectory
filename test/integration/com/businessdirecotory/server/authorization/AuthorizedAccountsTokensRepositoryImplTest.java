package com.businessdirecotory.server.authorization;

import com.businessdirecotory.AppEngineTestCase;
import com.businessdirecotory.client.authorization.Token;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizedAccountsTokensRepositoryImplTest extends AppEngineTestCase {


  private DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  private AuthorizedAccountsTokensRepositoryImpl repository;

  public AuthorizedAccountsTokensRepositoryImplTest() {

    super(new LocalDatastoreServiceTestConfig());

    repository = new AuthorizedAccountsTokensRepositoryImpl(service);
  }


  @Test
  public void addTokenToRepository() {
    String user = "user";
    Date date = new Date();
    Token token = new Token(user, date);
    repository.add(token);
    Query query = new Query("Token");
    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, token.getUser()),
            new Query.FilterPredicate("expireDate", Query.FilterOperator.EQUAL, token.getExpireDate())));

    Entity entity = service.prepare(query).asSingleEntity();
    assertThat(entity, is(notNullValue()));
    assertThat((String) entity.getProperty("username"), is(equalTo(user)));
    assertThat((Date) entity.getProperty("expireDate"), is(equalTo(date)));
  }

  @Test
  public void deletesTokenFromRepository() {
    Entity token = new Entity("Token");
    token.setProperty("username", "user");
    service.put(token);
    repository.delete(new Token("user"));
    Query query = new Query("Token");
    query.setFilter(new Query.FilterPredicate("username", Query.FilterOperator.EQUAL, "user"));
    Entity entity = service.prepare(query).asSingleEntity();
    assertThat(entity,is(nullValue()));
  }
}
