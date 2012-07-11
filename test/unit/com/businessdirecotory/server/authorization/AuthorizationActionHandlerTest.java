package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationActionHandlerTest {


  @Mock
  private CompaniesRepository repository;

  @Mock
  private AuthorizationActionHandler handler;

  @Mock
  private AuthorizedAccountsTokensRepository authorizedAccountTokensRepository;

  @Mock
  private SessionExpireTimeProvider expireTimeProvider;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new AuthorizationActionHandler(repository, authorizedAccountTokensRepository, expireTimeProvider);
  }

  @Test
  public void returnsResponseContainsTokenWhenUserIsRegistered() {

    Account account = new Account("user", "password");

    when(repository.isRegistered(account)).thenReturn(true);

    AuthorizationResponse response = handler.handle(new AuthorizationAction(account));

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(notNullValue()));

    assertThat(response.getToken().getUser(), is(equalTo("user")));

    verify(repository).isRegistered(account);

  }


  @Test
  public void addsTokenToAuthorizedAccountRepository() {

    Account account = new Account("username", "password");

    AuthorizationAction action = new AuthorizationAction(account);

    Calendar calendar = Calendar.getInstance();

    calendar.add(Calendar.DAY_OF_MONTH, 20);

    Token token = new Token("user", calendar.getTime());

    when(repository.isRegistered(account)).thenReturn(true);

    when(expireTimeProvider.getSessionExpireTime()).thenReturn(calendar.getTime());

    handler.handle(action);

    verify(expireTimeProvider).getSessionExpireTime();

    verify(authorizedAccountTokensRepository).add(Matchers.isA(Token.class));

  }


  @Test
  public void returnsResponseWithNullTokenValueWhenAccountIsNotRegistered() {

    Account account = new Account();

    when(repository.isRegistered(account)).thenReturn(false);

    AuthorizationResponse response = handler.handle(new AuthorizationAction(account));

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(equalTo(null)));

  }


}
