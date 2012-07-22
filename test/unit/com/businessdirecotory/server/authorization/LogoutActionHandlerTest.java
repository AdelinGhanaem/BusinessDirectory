package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.shared.entites.actions.LogoutAction;
import com.businessdirecotory.shared.entites.reponses.LogoutResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class LogoutActionHandlerTest {


  @Mock
  AuthorizedTokensRepository tokensRepository;

  LogoutActionHandler handler;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new LogoutActionHandler(tokensRepository);
  }

  @Test
  public void deleteTokenFromTokenRepositoryAndReturnsResponse() {
    String user = "user";

    Token token = new Token(user);

    LogoutResponse response = handler.handle(new LogoutAction(token));

    assertThat(response, is(notNullValue()));

    assertThat(response.getErrors(), is(notNullValue()));

    assertThat(response.getErrors().size(), is(notNullValue()));

    verify(tokensRepository).delete(token);

  }


//  @Test
//  public void returnsErrorListWhenTokenIsPreviouslyDeleted() {
//
//    String user = "user";
//
//    Token token = new Token(user);
//
//    LogoutResponse response = handler.handle(new LogoutAction(token));
//
//    assertThat(response, is(notNullValue()));
//
//    assertThat(response.getErrors(), is(notNullValue()));
//
//    assertThat(response.getErrors().size(), is(1));
//
//    verify(tokensRepository).delete(token);
//  }
}
