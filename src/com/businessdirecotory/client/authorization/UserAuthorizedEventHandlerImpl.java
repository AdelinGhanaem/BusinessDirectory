package com.businessdirecotory.client.authorization;

import com.businessdirecotory.client.navigation.NavigationBarView;
import com.businessdirecotory.client.navigation.NavigationBarViewImpl;
import com.businessdirecotory.client.navigation.WidgetsContainer;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserAuthorizedEventHandlerImpl implements UserAuthorizedEventHandler {

  private SecurityTokenProvider securityTokenProvider;

  private WidgetsContainer container;

  private NavigationBarViewImpl navigationBar;

  @Inject
  public UserAuthorizedEventHandlerImpl(SecurityTokenProvider securityTokenProvider,
                                        NavigationBarView navigationBarView, WidgetsContainer container) {
    this.securityTokenProvider = securityTokenProvider;
    this.container = container;
    this.navigationBar = (NavigationBarViewImpl) navigationBarView;
  }

  @Override
  public void onUserAuthorized(UserAuthorizedEvent event) {

    securityTokenProvider.setToken(event.getToken());

    navigationBar.setUsernameBrand(event.getToken().getUser());

    navigationBar.hideLoginMenuItem();

    navigationBar.showAuthorizedMenuBar();

    container.addToMenu(navigationBar);

  }
}
