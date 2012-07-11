package com.businessdirecotory.client;

import com.businessdirecotory.client.authorization.UserAuthorizedEvent;
import com.businessdirecotory.client.authorization.UserAuthorizedEventHandlerImpl;
import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.NavigationBarPresenter;
import com.businessdirecotory.client.navigation.NavigationBarView;
import com.businessdirecotory.client.navigation.NavigationBarViewImpl;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Businessdirecotory implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    RootPanel rootPanel = RootPanel.get("mainContainer");

    GinInjector injector = GWT.create(GinInjector.class);


    EventBus eventBus = injector.eventBus();

    eventBus.addHandler(UserAuthorizedEvent.TYPE,
            new UserAuthorizedEventHandlerImpl(injector.tokenProvider(), injector.navigationBar(), injector.widgetsContainer()));
    InjectablePlaceController placeController = injector.placeController();

    SimplePanel widgets = new SimplePanel();

    InjectableActivityManager activityManager = injector.injectableActivityManager();

    activityManager.setDisplay(widgets);


    NavigationBarViewImpl navigationBar = (NavigationBarViewImpl) injector.navigationBar();

    NavigationBarPresenter navigationBarPresenter = injector.navigationBarPresenter();

    navigationBar.setPresenter(navigationBarPresenter);

    RootPanel.get("navigation").add(navigationBar);

    RootPanel.get("mainContainer").add(widgets);

    placeController.goTo(new SearchPlace());

  }


}
