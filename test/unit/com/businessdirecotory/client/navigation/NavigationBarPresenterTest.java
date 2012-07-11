package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.client.authorization.UserAuthorizedEvent;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.actions.LogoutAction;
import com.businessdirecotory.shared.entites.reponses.LogoutResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class NavigationBarPresenterTest {


  @Mock
  private NavigationBarView view;

  @Mock
  private SecurityTokenProvider securityTokenProvider;

  @Mock
  WidgetsContainer container;

  @Mock
  private ActionDispatcherServiceAsync service;


  private NavigationBarPresenter presenter;


  @Before
  public void setUp() throws Exception {
    initMocks(this);
    presenter = new NavigationBarPresenter(securityTokenProvider, view, service);
  }

  @Test
  public void goToNewRegistrationPlaceOnNewRegistration() {

    presenter.onNewRegistration();

    verify(view).goToNewRegistration();

  }

  @Test
  public void goToAuthorizationOnLogIn() {

    presenter.onAuthorization();

    verify(view).gotToAuthorization();

  }

  @Test
  public void showAuthorizedUserMenuWhenUserIsAuthorized() {
    presenter.onUserAuthorized(new UserAuthorizedEvent(new Token("user")));
    verify(view).setUsernameBrand("user");
    verify(view).showAuthorizedMenuBar();
  }


  @Test
  public void setsSecurityToken() {

    String user = "user";

    Token token = new Token(user);

    presenter.onUserAuthorized(new UserAuthorizedEvent(token));

    verify(securityTokenProvider).setToken(token);
  }

  @Test
  public void showAuthorizedUserMenuBar() {

    String user = "user";

    Token token = new Token(user);

    presenter.onUserAuthorized(new UserAuthorizedEvent(token));

    verify(view).setUsernameBrand(user);

    verify(view).showLogoutButton();

    verify(view).hideLoginMenuItem();


  }

  @Test
  public void firesUserLogoutEventWhenUserLogsOutSuccessfully() {

    Token token = new Token("user");

    LogoutResponse logoutResponse = new LogoutResponse();

    doOnSuccess(logoutResponse).when(service).dispatch(isA(LogoutAction.class), isA(GotResponse.class));

    when(securityTokenProvider.getToken()).thenReturn(token);

    presenter.logout();

    verify(service).dispatch(isA(LogoutAction.class), isA(GotResponse.class));

    verify(securityTokenProvider).getToken();

    verify(view).setStandardMenu();
  }

}
