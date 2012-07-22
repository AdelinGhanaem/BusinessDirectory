package com.businessdirecotory.client;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.companyprofile.CompanyProfilePresenter;
import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.NavigationBarPresenter;
import com.businessdirecotory.client.navigation.NavigationBarView;
import com.businessdirecotory.client.navigation.NavigationBarViewImpl;
import com.businessdirecotory.client.navigation.WidgetsContainer;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@GinModules(GinModule.class)
public interface GinInjector extends Ginjector {

  EventBus eventBus();

  InjectablePlaceController placeController();

  InjectableActivityManager injectableActivityManager();

  NavigationBarView navigationBar();

  SecurityTokenProvider tokenProvider();

  WidgetsContainer widgetsContainer();

  NavigationBarPresenter navigationBarPresenter();

  CompanyProfilePresenter companyProfilePresenter();
}
