package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.shared.entites.actions.CheckAuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.CheckAuthorizationResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CheckAuthorizationActionHandlerTest {


  @Mock
  AuthorizedTokensRepository repository;

  @Mock
  IdGenerator idGenerator;


  CheckAuthorizationActionHandler handler;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new CheckAuthorizationActionHandler(repository, idGenerator);
  }

  @Test
  public void returnsTokenIfTokenIsAuthorized() {
    Token token = new Token();
    when(repository.isAuthorized(eq(token), isA(Date.class))).thenReturn(true);
    CheckAuthorizationResponse response = handler.handle(new CheckAuthorizationAction(token));
    verify(repository).isAuthorized(eq(token), isA(Date.class));
    assertThat(response, is(notNullValue()));
    assertThat(response.getToken(), is(notNullValue()));
  }
}
