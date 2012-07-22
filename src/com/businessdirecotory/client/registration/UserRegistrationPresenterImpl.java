package com.businessdirecotory.client.registration;

import com.businessdirecotory.client.registration.view.UserRegistrationView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationPresenterImpl extends AbstractActivity implements UserRegistrationPresenter {

  private final ActionDispatcherServiceAsync service;
  private final EventBus handlers;
  private final UserRegistrationView view;

  @Inject
  public UserRegistrationPresenterImpl(ActionDispatcherServiceAsync service, EventBus handlers, UserRegistrationView view) {
    this.service = service;
    this.handlers = handlers;
    this.view = view;
  }

  public void register(Company company) {
    service.dispatch(new CompanyRegistrationAction(company), new GotResponse<UserRegistrationResponse>() {
      @Override
      public void gotResponse(UserRegistrationResponse result) {
        if (result.getErrors().size() != 0) {
          view.showErrors(result.getErrors());
        } else {
          view.notifyOfAccountCreated();
        }
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
    view.setPresenter(this);
    view.show();
  }

}
