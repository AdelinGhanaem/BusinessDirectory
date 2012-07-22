package com.businessdirecotory.client.companyprofile;

import com.businessdirecotory.client.authorization.SecuredActionBuilder;
import com.businessdirecotory.client.authorization.SecuredResponse;
import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.client.companyprofile.view.CompanyProfileView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.client.authorization.SecuredAction;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.evo.gad.shared.Action;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyProfilePresenterTest {


  @Mock
  CompanyProfileView view;

  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  private SecuredActionBuilder securedActionBuilder;

  @Mock
  private SecurityTokenProvider provider;

  CompanyProfilePresenter companyProfilePresenter;

  @Captor
  ArgumentCaptor<SecuredAction<SecuredResponse<FetchCompanyResponse>>> captor;

  @Captor
  ArgumentCaptor<SecuredAction<SecuredResponse<EditCompanyResponse>>> securedActionArgumentCaptor;

  @Captor
  ArgumentCaptor<Company> companyCaptor;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    companyProfilePresenter = new CompanyProfilePresenter(service, view, securedActionBuilder, provider);

  }

  @Test
  public void fetchCompanyProfile() {

    String mail = "mail@mail.com";

    Token token = new Token(mail);

    Company companyProfile = new Company();

    FetchCompanyResponse response = new FetchCompanyResponse(companyProfile);

    SecuredResponse securedResponse = new SecuredResponse(response);

    FetchCompanyAction fetchCompanyAction = new FetchCompanyAction(mail);

    SecuredAction<SecuredResponse<FetchCompanyResponse>> securedAction =
            new SecuredAction<SecuredResponse<FetchCompanyResponse>>(fetchCompanyAction, token);

    when(provider.getToken()).thenReturn(token);

    when(securedActionBuilder.build(isA(FetchCompanyAction.class))).thenReturn(securedAction);

    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    companyProfilePresenter.fetchCompanyProfile();

    verify(securedActionBuilder).build(isA(FetchCompanyAction.class));

    verify(service).dispatch(captor.capture(), isA(GotResponse.class));

    SecuredAction<SecuredResponse<FetchCompanyResponse>> capturedSecuredAction = captor.getValue();

    assertThat(capturedSecuredAction, is(notNullValue()));

    Action action = captor.getValue().getAction();

    assertThat(capturedSecuredAction.getAction(), is(notNullValue()));

    assertThat(((FetchCompanyAction) action).getCompanyEmail(), is(equalTo(mail)));

    verify(provider).getToken();

    verify(view).showCompanyProfile(companyProfile);

  }

  @Test
  public void editsCompanyInformation() {
    Long id = 1l;
    Token token = new Token("username");
    Company company = new Company();
    company.setEmail("adelin@mail.com");
    company.setId(id);

    Company editedCompany = new Company();
    editedCompany.setEmail("mail@mail.com");
    editedCompany.setId(id);

    EditCompanyAction editCompanyAction = new EditCompanyAction(company);

    SecuredAction<SecuredResponse<EditCompanyResponse>> securedAction =
            new SecuredAction<SecuredResponse<EditCompanyResponse>>(editCompanyAction, token);

    EditCompanyResponse editCompanyResponse = new EditCompanyResponse(editedCompany);

    SecuredResponse<EditCompanyResponse> securedResponse = new SecuredResponse<EditCompanyResponse>(editCompanyResponse);

    when(securedActionBuilder.build(isA(EditCompanyAction.class))).thenReturn(securedAction);

    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    companyProfilePresenter.updateCompany(company);

    verify(service).dispatch(securedActionArgumentCaptor.capture(), isA(GotResponse.class));

    verify(view).showCompanyProfile(companyCaptor.capture());
    verify(view).enableEditButton();
    Company returnedCompany = companyCaptor.getValue();

    assertThat(returnedCompany.getEmail(), is(equalTo("mail@mail.com")));

  }


  @Test
  public void fetchUploadURL() {
//
//    String url = "someURL";
//
//    FetchUrlResponse response = new FetchUrlResponse(url);
//
//    doOnSuccess(response).when(service).dispatch(isA(FetchURLAction.class), isA(GotResponse.class));
//
//    companyProfilePresenter.fetchUploadURL();
//
//    verify(view).initializeUploadForm(url);
//
//    fail("implement me !");

  }


  @Test
  public void disablesEditButtonOnEdit() {
    companyProfilePresenter.updateCompany(new Company());
    verify(view).disableEditButton();
  }

  @Test
  public void enablesEditButton() {

  }


}
