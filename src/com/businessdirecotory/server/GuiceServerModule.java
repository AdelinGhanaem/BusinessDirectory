package com.businessdirecotory.server;

import com.businessdirecotory.client.comunication.FetchURLActionHandler;
import com.businessdirecotory.server.authorization.*;
import com.businessdirecotory.server.companyprofile.CompanyValidator;
import com.businessdirecotory.server.companyprofile.CompanyValidatorImpl;
import com.businessdirecotory.server.companyprofile.EditCompanyActionHandler;
import com.businessdirecotory.server.companyprofile.FetchCompanyActionHandler;
import com.businessdirecotory.server.companyprofile.FetchLogoActionHandler;
import com.businessdirecotory.server.comunication.ActionDispatcherServiceImpl;
import com.businessdirecotory.server.comunication.ImageUploader;
import com.businessdirecotory.server.registration.*;
import com.businessdirecotory.server.search.SearchActionHandler;
import com.businessdirecotory.shared.entites.actions.*;
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

import javax.validation.Validation;
import javax.validation.Validator;
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
        serve("/upload").with(ImageUploader.class);

        bind(ActionHandlerRepository.class).to(LazyActionHandlerRepository.class);
        bind(CompaniesRepository.class).to(CompaniesRepositoryImpl.class);
        bind(UserValidator.class).to(UserValidatorImpl.class);
        bind(CompanyValidationErrorMessages.class).to(CompanyValidationErrorMessagesImpl.class);
        bind(AuthorizedTokensRepository.class).to(AuthorizedTokensRepositoryImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(SessionIdProvider.class).to(SessionIdProviderImpl.class);
        bind(IdGenerator.class).to(IdGeneratorImpl.class);
        bind(CompanyValidator.class).to(CompanyValidatorImpl.class);

      }

      @Provides
      @Singleton
      public Set<ActionHandlerMetadata> metadataSet() {
        HashSet<ActionHandlerMetadata> metadatas = new HashSet<ActionHandlerMetadata>();
        metadatas.add(new ActionHandlerMetadata(UserRegistrationAction.class, UserRegistrationActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(SearchAction.class, SearchActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(AuthorizationAction.class, AuthorizationActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(LogoutAction.class, LogoutActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(FetchCompanyAction.class, FetchCompanyActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(FetchURLAction.class, FetchURLActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(EditCompanyAction.class, EditCompanyActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(FetchLogoAction.class, FetchLogoActionHandler.class));
        metadatas.add(new ActionHandlerMetadata(CheckAuthorizationAction.class, CheckAuthorizationActionHandler.class));
        return metadatas;
      }

      @Provides
      public DatastoreService datastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
      }

      @Provides
      Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
      }
    });
  }
}
