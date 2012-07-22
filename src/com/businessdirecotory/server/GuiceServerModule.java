package com.businessdirecotory.server;

import com.businessdirecotory.client.comunication.FetchURLActionHandler;
import com.businessdirecotory.server.authorization.AuthorizationActionHandler;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepository;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepositoryImpl;
import com.businessdirecotory.server.authorization.LogoutActionHandler;
import com.businessdirecotory.server.authorization.SessionExpireTimeProvider;
import com.businessdirecotory.server.authorization.SessionExpireTimeProviderImpl;
import com.businessdirecotory.server.companyprofile.EditCompanyActionHandler;
import com.businessdirecotory.server.companyprofile.FetchCompanyActionHandler;
import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.server.companyregistration.CompaniesRepositoryImpl;
import com.businessdirecotory.server.companyregistration.CompanyRegistrationActionHandler;
import com.businessdirecotory.server.companyregistration.CompanyValidationErrorMessages;
import com.businessdirecotory.server.companyregistration.CompanyValidationErrorMessagesImpl;
import com.businessdirecotory.server.companyregistration.CompanyValidator;
import com.businessdirecotory.server.companyregistration.CompanyValidatorImpl;
import com.businessdirecotory.server.comunication.ActionDispatcherServiceImpl;
import com.businessdirecotory.server.search.SearchActionHandler;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchURLAction;
import com.businessdirecotory.shared.entites.actions.LogoutAction;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.evo.gad.dispatch.ActionHandlerMetadata;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class GuiceServerModule extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        serve("/businessdirecotory/ActionDispatcherService").with(ActionDispatcherServiceImpl.class);
        serve("/upload").with(com.businessdirecotory.server.comunication.Uploader.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);
        bind(CompaniesRepository.class).to(CompaniesRepositoryImpl.class);
        bind(CompanyValidator.class).to(CompanyValidatorImpl.class);
        bind(CompanyValidationErrorMessages.class).to(CompanyValidationErrorMessagesImpl.class);
        bind(SessionExpireTimeProvider.class).to(SessionExpireTimeProviderImpl.class);
        bind(AuthorizedTokensRepository.class).to(AuthorizedTokensRepositoryImpl.class);
      }

      @Provides
      @Singleton
      public Set<ActionHandlerMetadata> metadataSet() {
        HashSet<ActionHandlerMetadata> metadatas = new HashSet<ActionHandlerMetadata>();
        metadatas.add(new ActionHandlerMetadata(CompanyRegistrationAction.class, CompanyRegistrationActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(SearchAction.class, SearchActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(AuthorizationAction.class, AuthorizationActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(LogoutAction.class, LogoutActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(FetchCompanyAction.class, FetchCompanyActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(FetchURLAction.class, FetchURLActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(EditCompanyAction.class, EditCompanyActionHandler.class));

        return metadatas;
      }

      @Provides
      public DatastoreService datastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
      }
    });
  }
}
