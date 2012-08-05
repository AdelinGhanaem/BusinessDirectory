package com.businessdirecotory.client.authorization;

import com.businessdirecotory.client.authorization.view.AuthorizationView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.User;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.google.web.bindery.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationPresenterTest {


  @Mock
  private ActionDispatcherServiceAsync service;

  @Mock
  private SecurityTokenProvider securityTokenProvider;

  @Mock
  private AuthorizationView view;

  @Mock
  EventBus eventBus;

  AuthorizationPresenter presenter;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    presenter = new AuthorizationPresenter(service, eventBus, view);
  }


//  @Test
//  public void dispatchesAuthorizationAction() {
//
//    Account account = new Account();
//
//    presenter.authorize(account);
//
//    verify(service).dispatch(isA(AuthorizationAction.class), isA(GotResponse.class));
//  }

//  @Test
//  public void setsTokenWhenAuthorizationSucceed() {


//    verify(securityTokenProvider).setToken(token);
//
//    verify(view).hide();
//  }

  @Test
  public void firesUserAuthorizedEventAndHidesLogInViewWhenUserIsAuthorized() {

    Token token = new Token(1l, 1l, "username", new Date());

    AuthorizationResponse response = new AuthorizationResponse(token);

    doOnSuccess(response).when(service).dispatch(isA(AuthorizationAction.class), isA(GotResponse.class));

    presenter.authorize(new User());

    verify(service).dispatch(isA(AuthorizationAction.class), isA(GotResponse.class));

    verify(eventBus).fireEvent(isA(UserAuthorizedEvent.class));

    verify(view).hide();

//    fail();
  }


  @Test
  public void notifiesUserWhenUsernameOrPasswordAreWrong() {

    String user = "user";

    AuthorizationResponse response = new AuthorizationResponse(null);

    doOnSuccess(response).when(service).dispatch(isA(AuthorizationAction.class), isA(GotResponse.class));

    presenter.authorize(new User());

    verify(service).dispatch(isA(AuthorizationAction.class), isA(GotResponse.class));

    verify(securityTokenProvider, never()).setToken(null);

    verify(view).notifyUserOfWrongUsernameOrPassword();

    verify(view, never()).hide();
  }


}
