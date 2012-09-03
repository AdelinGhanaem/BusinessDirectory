package com.businessdirecotory.client;

import com.businessdirecotory.client.authorization.SecuredActionBuilder;
import com.businessdirecotory.client.authorization.SecuredActionBuilderImpl;
import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.SecurityTokenProviderImpl;
import com.businessdirecotory.client.authorization.UserAuthorizedEventHandler;
import com.businessdirecotory.client.authorization.UserAuthorizedEventHandlerImpl;
import com.businessdirecotory.client.authorization.view.AuthorizationView;
import com.businessdirecotory.client.authorization.view.AuthorizationViewImpl;
import com.businessdirecotory.client.companyprofile.view.CompanyProfileView;
import com.businessdirecotory.client.companyprofile.view.CompanyProfileViewImpl;
import com.businessdirecotory.client.comunication.ActionDispatcherService;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.navigation.ActivityPlaceMeta;
import com.businessdirecotory.client.navigation.ApplicationActivityMapper;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.NavigationBarView;
import com.businessdirecotory.client.navigation.NavigationBarViewImpl;
import com.businessdirecotory.client.navigation.WidgetsContainer;
import com.businessdirecotory.client.registration.view.UserRegistrationView;
import com.businessdirecotory.client.registration.view.UserRegistrationViewImpl;
import com.businessdirecotory.client.search.CompanyFullInfoView;
import com.businessdirecotory.client.search.SearchView;
import com.businessdirecotory.client.search.companyprofileview.CompanyFullInfoViewImpl;
import com.businessdirecotory.client.search.view.SearchViewImpl;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import java.util.HashMap;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class GinModule extends AbstractGinModule {

  @Override
  protected void configure() {

    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

    bind(new TypeLiteral<HashMap<Class<? extends Place>, ActivityPlaceMeta>>() {
    }).toProvider(ActivityPlacesMapProvider.class).in(Singleton.class);

    //Binding view and presenters
    bind(UserRegistrationView.class).to(UserRegistrationViewImpl.class);

    bind(ActivityMapper.class).to(ApplicationActivityMapper.class);

    bind(SearchView.class).to(SearchViewImpl.class);

    bind(AuthorizationView.class).to(AuthorizationViewImpl.class).in(Singleton.class);

    bind(SecurityTokenProvider.class).to(SecurityTokenProviderImpl.class);

    bind(NavigationBarView.class).to(NavigationBarViewImpl.class).in(Singleton.class);

    bind(CompanyProfileView.class).to(CompanyProfileViewImpl.class);

    bind(UserAuthorizedEventHandler.class).to(UserAuthorizedEventHandlerImpl.class);

    bind(SecuredActionBuilder.class).to(SecuredActionBuilderImpl.class);

    bind(WidgetsContainer.class).to(WidgetsContainerImpl.class);

    bind(CompanyFullInfoView.class).to(CompanyFullInfoViewImpl.class).in(Singleton.class);

    bind(InjectablePlaceController.class).in(Singleton.class);

  }

  @Provides
  ActionDispatcherServiceAsync provideService() {
    return GWT.create(ActionDispatcherService.class);
  }


}
