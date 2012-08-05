package com.businessdirecotory.client.companyInformation;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.CompanyInfo;
import com.businessdirecotory.shared.entites.actions.GetCompanyInfoAction;
import com.businessdirecotory.shared.entites.reponses.GetCompanyInfoResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyInfoPresenterTest {


  CompanyInfoPresenter companyInfoPresenter;

  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  private CompanyInfoView view;


  @Before
  public void setUp() throws Exception {
    initMocks(this);
    companyInfoPresenter = new CompanyInfoPresenter(service, view);
  }

  @Test
  public void getsCompanyInformationAndShowReturnedResult() {

    Long companyId = 1l;

    CompanyInfo companyInfo = new CompanyInfo();

    GetCompanyInfoResponse response = new GetCompanyInfoResponse(companyInfo);

    doOnSuccess(response).when(service).dispatch(isA(GetCompanyInfoAction.class), isA(GotResponse.class));

    companyInfoPresenter.getInfo(companyId);

    verify(service).dispatch(isA(GetCompanyInfoAction.class), isA(GotResponse.class));

    verify(view).showCompanyInfo(isA(companyInfo.getClass()));

  }


  @Test
  public void notifiesUserWhenCompanyInfoIsNullValue() {
    Long companyId = 1l;

    CompanyInfo companyInfo = null;

    GetCompanyInfoResponse response = new GetCompanyInfoResponse(companyInfo);

    doOnSuccess(response).when(service).dispatch(isA(GetCompanyInfoAction.class), isA(GotResponse.class));

    companyInfoPresenter.getInfo(companyId);

    verify(service).dispatch(isA(GetCompanyInfoAction.class), isA(GotResponse.class));

    verify(view, never()).showCompanyInfo(isA(CompanyInfo.class));

    verify(view).notifyUserNoCompanyInfo();



  }

}
