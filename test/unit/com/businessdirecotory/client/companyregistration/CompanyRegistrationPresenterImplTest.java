package com.businessdirecotory.client.companyregistration;

import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationPresenterImplTest {


  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  CompanyRegistrationView view;

  @Mock
  EventBus handlers;

  CompanyRegistrationPresenterImpl companyRegistrationPresenterImpl;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    companyRegistrationPresenterImpl = new CompanyRegistrationPresenterImpl(service, handlers, view);
  }

  @Test
  public void registersAccountAndFiresAccountCreatedEvent() {

    CompanyRegistrationResponse response = new CompanyRegistrationResponse(new ArrayList<String>());

    Company account = new Company();

    doOnSuccess(response).when(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    companyRegistrationPresenterImpl.register(account);

    verify(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    verify(handlers).fireEvent(isA(CompanyRegisteredEvent.class));
  }


  @Test
  public void showErrorsWhenRegistrationHasErrors() {

    ArrayList<String> errors = new ArrayList<String>();

    errors.add("errors");

    CompanyRegistrationResponse response = new CompanyRegistrationResponse(errors);

    doOnSuccess(response).when(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    Company account = new Company();

    companyRegistrationPresenterImpl.register(account);

    verify(service).dispatch(isA(CompanyRegistrationAction.class), isA(GotResponse.class));

    verify(handlers, never()).fireEvent(isA(CompanyRegisteredEvent.class));
  }

}
