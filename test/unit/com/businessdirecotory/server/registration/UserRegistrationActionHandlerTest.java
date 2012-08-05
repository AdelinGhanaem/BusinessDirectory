package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.actions.UserRegistrationAction;
import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationActionHandlerTest {


  @Mock
  CompaniesRepository companiesRepository;

  UserRegistrationActionHandler handlerUser;

  @Mock
  UserValidator validator;

  @Mock
  UserRepository repository;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handlerUser = new UserRegistrationActionHandler(repository,validator);

  }


  @Test
  public void addsUsersToUserRepository() {
    User user = new User("mail@mail.com", "password");
    when(companiesRepository.add(isA(Company.class))).thenReturn(1l);
    UserRegistrationResponse response = handlerUser.handle(new UserRegistrationAction(user));
    verify(repository).add("mail@mail.com", "password");
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(equalTo(0)));
  }

  @Test
  public void userWithReservedEmailIsNotAdded() {
    when(repository.isEmailReserved("username")).thenReturn(true);
    when(companiesRepository.add(isA(Company.class))).thenReturn(1l);
    UserRegistrationResponse response = handlerUser.handle(new UserRegistrationAction(new User(0l, "username", "")));
    verify(repository, never()).add("username", "password");
    verify(companiesRepository, never()).add(isA(Company.class));
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(equalTo(1)));
  }

  @Test
  public void userIsNotRegisteredIfItsNotValid() {
    User user = new User(0l, "username", "password");
    when(validator.validate(user)).thenReturn(new ArrayList<String>() {{
      add("String");
    }});
    UserRegistrationResponse response = handlerUser.handle(new UserRegistrationAction(user));
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(1));
    verify(repository, never()).add( any(String.class), any(String.class));

  }

}
