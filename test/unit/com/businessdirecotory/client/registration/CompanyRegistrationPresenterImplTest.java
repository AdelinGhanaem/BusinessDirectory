package com.businessdirecotory.client.registration;

import com.businessdirecotory.client.registration.view.UserRegistrationView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
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
public class CompanyRegistrationPresenterImplTest {


  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  UserRegistrationView view;

  @Mock
  EventBus handlers;

  UserRegistrationPresenterImpl companyRegistrationPresenterImpl;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    companyRegistrationPresenterImpl = new UserRegistrationPresenterImpl(service, handlers, view);
  }

  @Test
  public void registersAccountAndNotifiesUserWhenAccountIsCreated() {

    UserRegistrationResponse response = new UserRegistrationResponse(new ArrayList<String>());

    Company account = new Company();

    doOnSuccess(response).when(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    companyRegistrationPresenterImpl.register(account);

    verify(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    verify(view).notifyOfAccountCreated();
  }


  @Test
  public void showErrorsWhenRegistrationHasErrors() {

    ArrayList<String> errors = new ArrayList<String>();

    errors.add("errors");

    UserRegistrationResponse response = new UserRegistrationResponse(errors);

    doOnSuccess(response).when(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    Company account = new Company();

    companyRegistrationPresenterImpl.register(account);

    verify(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    verify(view).showErrors(errors);
  }
}
