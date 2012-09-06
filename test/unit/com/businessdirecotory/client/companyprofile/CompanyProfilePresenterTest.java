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
import com.businessdirecotory.shared.entites.actions.FetchCompanyByUserIdAction;
import com.businessdirecotory.shared.entites.actions.FetchLogoAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyByUserIdResponse;
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
  ArgumentCaptor<SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>>> captor;

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


    Token token = new Token(2l, 2l, "mail@mail.com", new Date());

    Company company = new Company();

    FetchCompanyByUserIdResponse response = new FetchCompanyByUserIdResponse(company);

    SecuredResponse securedResponse = new SecuredResponse(response);

    FetchCompanyByUserIdAction action = new  FetchCompanyByUserIdAction(2l);

    SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>> securedAction =
            new SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>>(action, token);

    when(provider.getToken()).thenReturn(token);

    when(securedActionBuilder.build(isA(FetchCompanyByUserIdAction.class))).thenReturn(securedAction);

    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.fetchCompanyProfile();

    verify(securedActionBuilder).build(isA(FetchCompanyByUserIdAction.class));

    verify(service).dispatch(captor.capture(), isA(GotResponse.class));

    SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>> capturedSecuredAction = captor.getValue();

    assertThat(capturedSecuredAction, is(notNullValue()));

    assertThat(capturedSecuredAction.getAction(), is(notNullValue()));

    verify(provider).getToken();

    verify(view).showCompanyProfile(company);
  }

  @Test
  public void showCreateButtonIfReturnedCompanyIsNull() {

    String mail = "mail@mail.com";

    Long tokenId = 12l;
    Long usernameId = 12l;
    Token token = new Token(tokenId, usernameId, mail, new Date());

    Company companyProfile = null;

    FetchCompanyByUserIdResponse byIdResponse = new FetchCompanyByUserIdResponse(companyProfile);

    SecuredResponse securedResponse = new SecuredResponse(byIdResponse);

    FetchCompanyByUserIdAction fetchCompanyByIdAction = new FetchCompanyByUserIdAction(usernameId);

    SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>> securedAction =
            new SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>>(fetchCompanyByIdAction, token);

    when(provider.getToken()).thenReturn(token);

    when(securedActionBuilder.build(isA(FetchCompanyByUserIdAction.class))).thenReturn(securedAction);

    doOnSuccess(securedResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.fetchCompanyProfile();

    verify(securedActionBuilder).build(isA(FetchCompanyByUserIdAction.class));

    verify(service).dispatch(captor.capture(), isA(GotResponse.class));

    SecuredAction<SecuredResponse<FetchCompanyByUserIdResponse>> capturedSecuredAction = captor.getValue();

    assertThat(capturedSecuredAction, is(notNullValue()));

    Action action = captor.getValue().getAction();

    assertThat(capturedSecuredAction.getAction(), is(notNullValue()));

    verify(provider).getToken();

    verify(view).showCreateProfileButton();

    verify(view, never()).showCompanyProfile(companyProfile);
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

    SecuredAction<SecuredResponse<FetchLogoResponse>> securedAction = new SecuredAction<SecuredResponse<FetchLogoResponse>>(fetchLogoAction, token);

    when(provider.getToken()).thenReturn(token);

    when(securedActionBuilder.build(isA(FetchLogoAction.class))).thenReturn(securedAction);

    doOnSuccess(fetchLogoResponseSecuredResponse).when(service).dispatch(isA(SecuredAction.class), isA(GotResponse.class));

    presenter.fetchImage();

    verify(view).setURL(imageURL);

  }
}
