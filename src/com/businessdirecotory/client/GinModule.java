package com.businessdirecotory.client;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenter;
import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationView;
import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationViewImpl;
import com.businessdirecotory.client.comunication.ActionDispatcherService;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.navigation.ApplicationActivityMapper;
import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.search.SearchView;
import com.businessdirecotory.client.search.view.SearchViewImpl;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class GinModule extends AbstractGinModule {

  @Override
  protected void configure() {

    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

    bind(new TypeLiteral<HashMap<Class<? extends Place>, Activity>>() {
    }).toProvider(ActivityPlacesMapProvider.class).in(Singleton.class);

    //Binding view and presenters
    bind(CompanyRegistrationView.class).to(CompanyRegistrationViewImpl.class);

    bind(ActivityMapper.class).to(ApplicationActivityMapper.class);

    bind(SearchView.class).to(SearchViewImpl.class);
  }

  @Provides
  ActionDispatcherServiceAsync provideService() {
    return GWT.create(ActionDispatcherService.class);
  }


}
