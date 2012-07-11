package com.businessdirecotory.client.companyregistration;

import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationPresenterImpl extends AbstractActivity implements CompanyRegistrationPresenter {

  private final ActionDispatcherServiceAsync service;
  private final EventBus handlers;
  private final CompanyRegistrationView view;

  @Inject
  public CompanyRegistrationPresenterImpl(ActionDispatcherServiceAsync service, EventBus handlers, CompanyRegistrationView view) {
    this.service = service;
    this.handlers = handlers;
    this.view = view;
  }

  public void register(Company company) {
    service.dispatch(new CompanyRegistrationAction(company), new GotResponse<CompanyRegistrationResponse>() {
      @Override
      public void gotResponse(CompanyRegistrationResponse result) {
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
