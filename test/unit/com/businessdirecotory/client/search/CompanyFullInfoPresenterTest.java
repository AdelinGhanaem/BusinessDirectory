package com.businessdirecotory.client.search;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyByIdAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyByIdResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyFullInfoPresenterTest {


  @Mock
  CompanyFullInfoView view;

  @Mock
  ActionDispatcherServiceAsync service;


  CompanyFullInfoPresenter presenter;


  @Before
  public void setUp() throws Exception {
    initMocks(this);
    presenter = new CompanyFullInfoPresenter(service, view);
  }


  @Test
  public void fetchCompany() {

    FetchCompanyByIdResponse byIdResponse = new FetchCompanyByIdResponse(new Company());

    doOnSuccess(byIdResponse).when(service).dispatch(ArgumentCaptor.
            forClass(FetchCompanyByIdAction.class).capture(), isA(GotResponse.class));

    presenter.fetchFullInfo(1l);

    verify(view).viewCompany(isA(Company.class));

  }

  @Test
  public void notifiesUseIfCompanyIsNull() {

    FetchCompanyByIdResponse byIdResponse = new FetchCompanyByIdResponse();

    doOnSuccess(byIdResponse).when(service).dispatch(ArgumentCaptor.
            forClass(FetchCompanyByIdAction.class).capture(), isA(GotResponse.class));

    presenter.fetchFullInfo(1l);

    verify(view, never()).viewCompany(isA(Company.class));

    verify(view).notifyUserWhenCompanyIsNull();
  }


}
