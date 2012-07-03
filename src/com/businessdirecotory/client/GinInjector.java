package com.businessdirecotory.client;

import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.NavigationBar;
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

  NavigationBar navigationBar();


}
