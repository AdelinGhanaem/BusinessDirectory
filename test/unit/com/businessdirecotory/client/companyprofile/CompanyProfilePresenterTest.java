package com.businessdirecotory.client.companyprofile;

import com.businessdirecotory.client.authorization.SecuredAction;
import com.businessdirecotory.client.authorization.SecuredActionBuilder;
import com.businessdirecotory.client.authorization.SecuredResponse;
import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.client.companyprofile.view.CompanyProfileView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchLogoAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.businessdirecotory.shared.entites.reponses.FetchLogoResponse;
import com.evo.gad.shared.Action;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Date;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
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

  CompanyProfilePresenter presenter;

  @Captor
  ArgumentCaptor<SecuredAction<SecuredResponse<FetchCompanyResponse>>> captor;

  @Captor
  ArgumentCaptor<SecuredAction<SecuredResponse<EditCompanyResponse>>> securedActionArgumentCaptor;

  @Captor
  ArgumentCaptor<Company> companyCaptor;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    presenter = new CompanyProfilePresenter(service, view, securedActionBuilder, provider);

  }

  @Test
  public void fetchCompanyProfile() {

    String mail = "mail@mail.com";

    Token token = new Token(2l, 2l, mail, new Date());

    Company companyProfile = new Company();

    FetchCompanyResponse response = new FetchCompanyResponse(companyProfile);

    SecuredResponse securedResponse = new SecuredResponse(response);

    FetchCompanyAction fetchCompanyAction = new FetchCompanyAction(2l);

    SecuredAction<SecuredResponse<FetchCompanyResponse>> securedAction =
            new SecuredAction<SecuredResponse<FetchCompanyResponse>>(fetchCompanyAction, token);

    when(provider.getToken()).thenReturn(token);

    when(securedActionBuilder.build(isA(FetchCompanyAction.class))).thenReturn(securedAction);

    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.fetchCompanyProfile();

    verify(securedActionBuilder).build(isA(FetchCompanyAction.class));

    verify(service).dispatch(captor.capture(), isA(GotResponse.class));

    SecuredAction<SecuredResponse<FetchCompanyResponse>> capturedSecuredAction = captor.getValue();

    assertThat(capturedSecuredAction, is(notNullValue()));

    assertThat(capturedSecuredAction.getAction(), is(notNullValue()));

    verify(provider).getToken();

    verify(view).showCompanyProfile(companyProfile);

  }

  @Test
  public void showCreateButtonIfReturnedCompanyIsNull() {

    String mail = "mail@mail.com";

    Long tokenId = 12l;
    Long usernameId = 12l;
    Token token = new Token(tokenId, usernameId, mail, new Date());

    Company companyProfile = null;

    FetchCompanyResponse response = new FetchCompanyResponse(companyProfile, null);

    SecuredResponse securedResponse = new SecuredResponse(response);

    FetchCompanyAction fetchCompanyAction = new FetchCompanyAction(usernameId);

    SecuredAction<SecuredResponse<FetchCompanyResponse>> securedAction =
            new SecuredAction<SecuredResponse<FetchCompanyResponse>>(fetchCompanyAction, token);

    when(provider.getToken()).thenReturn(token);

    when(securedActionBuilder.build(isA(FetchCompanyAction.class))).thenReturn(securedAction);

    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.fetchCompanyProfile();

    verify(securedActionBuilder).build(isA(FetchCompanyAction.class));

    verify(service).dispatch(captor.capture(), isA(GotResponse.class));

    SecuredAction<SecuredResponse<FetchCompanyResponse>> capturedSecuredAction = captor.getValue();

    assertThat(capturedSecuredAction, is(notNullValue()));

    Action action = captor.getValue().getAction();

    assertThat(capturedSecuredAction.getAction(), is(notNullValue()));

    assertThat(((FetchCompanyAction) action).getId(), is(equalTo(usernameId)));

    verify(provider).getToken();

    verify(view).showCreateProfileButton();

    verify(view, never()).showCompanyProfile(companyProfile);

  }

  @Test
  public void editsCompanyInformation() {
    Long id = 1l;
    Token token = new Token(2l, 5l, "username", new Date());
    Company company = new Company();
    company.setEmail("adelin@mail.com");
    company.setId(id);

    Company editedCompany = new Company();
    editedCompany.setEmail("mail@mail.com");
    editedCompany.setId(id);

    EditCompanyAction editCompanyAction = new EditCompanyAction(1l, company);

    SecuredAction<SecuredResponse<EditCompanyResponse>> securedAction =
            new SecuredAction<SecuredResponse<EditCompanyResponse>>(editCompanyAction, token);

    EditCompanyResponse editCompanyResponse = new EditCompanyResponse(editedCompany, null);

    SecuredResponse<EditCompanyResponse> securedResponse = new SecuredResponse<EditCompanyResponse>(editCompanyResponse);

    when(securedActionBuilder.build(isA(EditCompanyAction.class))).thenReturn(securedAction);
    when(provider.getToken()).thenReturn(token);
    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.updateCompany(company);

    verify(service).dispatch(securedActionArgumentCaptor.capture(), isA(GotResponse.class));

    verify(view).showCompanyProfile(companyCaptor.capture());

    verify(view).enableEditButton();

    Company returnedCompany = companyCaptor.getValue();

    assertThat(returnedCompany.getEmail(), is(equalTo("mail@mail.com")));

  }


  @Test
  public void fetchesCompanyImage() {

    Long userId = 1l;

    String imageURL = "url";

    FetchLogoAction fetchLogoAction = new FetchLogoAction(userId);

    FetchLogoResponse response = new FetchLogoResponse(imageURL);

    SecuredResponse<FetchLogoResponse> fetchLogoResponseSecuredResponse =
            new SecuredResponse<FetchLogoResponse>(response);

    Token token = new Token(1l, 1l, "username", new Date());

    SecuredAction<SecuredResponse<FetchLogoResponse>> securedAction =
            new SecuredAction<SecuredResponse<FetchLogoResponse>>(fetchLogoAction, token);
    when(provider.getToken()).thenReturn(token);
    when(securedActionBuilder.build(isA(FetchLogoAction.class))).thenReturn(securedAction);

    doOnSuccess(fetchLogoResponseSecuredResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.fetchImage();

    verify(view).updateImageURL(imageURL);

  }
}
