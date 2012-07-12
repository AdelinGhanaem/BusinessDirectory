package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.UserAuthorizedEvent;
import com.businessdirecotory.client.authorization.UserAuthorizedEventHandler;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.actions.LogoutAction;
import com.businessdirecotory.shared.entites.reponses.LogoutResponse;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class NavigationBarPresenter implements UserAuthorizedEventHandler {

  private SecurityTokenProvider securityTokenProvider;
  private NavigationBarView view;
  private ActionDispatcherServiceAsync service;

  @Inject
  public NavigationBarPresenter(
          SecurityTokenProvider securityTokenProvider,
          NavigationBarView view,
          ActionDispatcherServiceAsync service) {
    this.securityTokenProvider = securityTokenProvider;
    this.view = view;


    this.service = service;
  }

  public void onNewRegistration() {
    view.goToNewRegistration();
  }

  public void onAuthorization() {
    view.gotToAuthorization();

  }

  public void logout() {
    service.dispatch(new LogoutAction(securityTokenProvider.getToken()), new GotResponse<LogoutResponse>() {
      @Override
      public void gotResponse(LogoutResponse result) {
        view.setStandardMenu();
      }
    });
  }


  @Override
  public void onUserAuthorized(UserAuthorizedEvent event) {

    securityTokenProvider.setToken(event.getToken());

    view.setUsernameBrand(event.getToken().getUser());

//    view.hideLoginMenuItem();
//
//    view.showLogoutButton();

    view.showAuthorizedMenuBar();
  }
}
