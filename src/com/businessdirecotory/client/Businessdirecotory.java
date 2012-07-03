package com.businessdirecotory.client;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenter;
import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenterImpl;
import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationViewImpl;
import com.businessdirecotory.client.comunication.ActionDispatcherService;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.NavigationBar;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
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

    InjectablePlaceController placeController = injector.placeController();

    SimplePanel widgets = new SimplePanel();

    InjectableActivityManager activityManager = injector.injectableActivityManager();

    activityManager.setDisplay(widgets);


    NavigationBar navigationBar = injector.navigationBar();

    RootPanel.get("navigation").add(navigationBar);

    RootPanel.get("mainContainer").add(widgets);
    placeController.goTo(new SearchPlace());

  }


}
