package com.businessdirecotory.client;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.client.authorization.UserAuthorizedEvent;
import com.businessdirecotory.client.authorization.UserAuthorizedEventHandlerImpl;
import com.businessdirecotory.client.comunication.ActionDispatcherService;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.client.navigation.ApplicationPlaceHistoryMapper;
import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.NavigationBarPresenter;
import com.businessdirecotory.client.navigation.NavigationBarViewImpl;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.businessdirecotory.client.test.Tabs;
import com.businessdirecotory.shared.entites.actions.CheckAuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.CheckAuthorizationResponse;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Businessdirecotory implements EntryPoint {

  /**
   * This is the entry point method.
   */

  public void onModuleLoad() {

    GinInjector injector = GWT.create(GinInjector.class);

    ActionDispatcherServiceAsync service = GWT.create(ActionDispatcherService.class);

    final SecurityTokenProvider provider = injector.tokenProvider();

    EventBus eventBus = injector.eventBus();

    eventBus.addHandler(UserAuthorizedEvent.TYPE,
            new UserAuthorizedEventHandlerImpl(injector.tokenProvider(), injector.navigationBar(), injector.widgetsContainer()));

    InjectablePlaceController placeController = injector.placeController();

    SimplePanel widgets = new SimplePanel();

    InjectableActivityManager activityManager = injector.injectableActivityManager();

    activityManager.setDisplay(widgets);

    final NavigationBarViewImpl navigationBar = (NavigationBarViewImpl) injector.navigationBar();

    NavigationBarPresenter navigationBarPresenter = injector.navigationBarPresenter();

    navigationBar.setPresenter(navigationBarPresenter);

    navigationBar.showStandardMenu();

    RootPanel.get("navigation").add(navigationBar);

    service.dispatch(new CheckAuthorizationAction(provider.getToken()), new GotResponse<CheckAuthorizationResponse>() {
      @Override
      public void gotResponse(CheckAuthorizationResponse result) {
        Token token = result.getToken();
        if (token != null) {
          provider.setToken(token);
          navigationBar.setUsernameBrand(token.getUser());
          navigationBar.hideLoginMenuItem();
          navigationBar.showAuthorizedMenuBar();

        } else {
          navigationBar.showStandardMenu();
          RootPanel.get("navigation").add(navigationBar);
        }

      }
    });

    ApplicationPlaceHistoryMapper historyMapper = GWT.create(ApplicationPlaceHistoryMapper.class);

    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

    historyHandler.register(placeController, eventBus, new SearchPlace());

    Tabs tabs = new Tabs();
    RootPanel.get("mainContainer").add(tabs);

    historyHandler.handleCurrentHistory();

//    CompanyProfileViewImpl view = new CompanyProfileViewImpl();
//    NavigationBarViewImpl view = new NavigationBarViewImpl();
//    CompanyProfileViewImpl view = new CompanyProfileViewImpl();
//    RootPanel.get().add(view);
  }


}
