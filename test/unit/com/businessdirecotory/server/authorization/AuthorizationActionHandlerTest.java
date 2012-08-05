package com.businessdirecotory.server.authorization;

import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.server.registration.UserValidator;
import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationActionHandlerTest {


  @Mock
  private UserRepository userRepository;


  @Mock
  private AuthorizationActionHandler handler;

  @Mock
  private AuthorizedTokensRepository authorizedTokensRepository;

  @Mock
  IdGenerator idGenerator;
  @Mock
  private UserValidator validator;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new AuthorizationActionHandler(userRepository,
            authorizedTokensRepository,
            idGenerator, validator);
  }

  @Test
  public void returnsResponseContainsTokenWhenUserIsRegistered() {

    User account = new User("username", "password");

    when(userRepository.getUserId("username", "password")).thenReturn(1l);

    when(idGenerator.generateId()).thenReturn(21l);

    AuthorizationResponse response = handler.handle(new AuthorizationAction(account));

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(notNullValue()));

    assertThat(response.getToken().getUser(), is(equalTo("username")));

    assertThat(response.getToken().getTokenId(), is(equalTo(21l)));

    assertThat(response.getToken().getExpireDate(), is(notNullValue()));

    verify(idGenerator).generateId();

    verify(userRepository).getUserId("username", "password");
  }

  @Test
  public void returnsResponseWithNullTokenValueWhenAccountIsNotRegistered() {

    User account = new User(0l, "username", "password");

    when(userRepository.getUserId(account.getUsername(), account.getPassword())).thenReturn(null);

    AuthorizationResponse response = handler.handle(new AuthorizationAction(account));

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(equalTo(null)));

  }

  @Test
  public void userValidation() {
    User user = new User("mail@asd", "12");

    when(validator.validate(user)).thenReturn(new ArrayList<String>() {{
      add("validator");
    }});

    AuthorizationResponse response = handler.handle(new AuthorizationAction(user));

    verify(userRepository, never()).getUser(user.getUsername(), user.getPassword());

    assertThat(response, is(notNullValue()));

    assertThat(response.getToken(), is(equalTo(null)));

  }


}
