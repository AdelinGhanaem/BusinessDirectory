package com.businessdirecotory.client.registration;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.client.registration.view.UserRegistrationView;
import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.actions.UserRegistrationAction;
import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationPresenterImplTest {


  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  UserRegistrationView view;

  @Mock
  EventBus handlers;

  UserRegistrationPresenterImpl userRegistrationPresenterImpl;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    userRegistrationPresenterImpl = new UserRegistrationPresenterImpl(service, handlers, view);
  }

  @Test
  public void registersUserAndNotifiesUserWhenAccountIsCreated() {

    UserRegistrationResponse response = new UserRegistrationResponse(new ArrayList<String>());

    User user = new User(0l, "username", "password");

    doOnSuccess(response).when(service).dispatch(isA(UserRegistrationAction.class), isA(GotResponse.class));

    userRegistrationPresenterImpl.register(user);

    verify(service).dispatch(isA(UserRegistrationAction.class), isA(GotResponse.class));

    verify(view).notifyOfAccountCreated();
  }


  @Test
  public void showErrorsWhenRegistrationHasErrors() {

    ArrayList<String> errors = new ArrayList<String>();

    errors.add("errors");

    UserRegistrationResponse response = new UserRegistrationResponse(errors);

    doOnSuccess(response).when(service).dispatch(isA(UserRegistrationAction.class), isA(GotResponse.class));

    User user = new User(0l, "mail@mail.com", "password");

    userRegistrationPresenterImpl.register(user);

    verify(service).dispatch(isA(UserRegistrationAction.class), isA(GotResponse.class));

    verify(view).showErrors(errors);
  }


}
