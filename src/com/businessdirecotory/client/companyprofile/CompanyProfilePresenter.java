package com.businessdirecotory.client.companyprofile;

import com.businessdirecotory.client.authorization.SecuredAction;
import com.businessdirecotory.client.authorization.SecuredActionBuilder;
import com.businessdirecotory.client.authorization.SecuredResponse;
import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.companyprofile.view.CompanyProfileView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.actions.FetchURLAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.businessdirecotory.shared.entites.reponses.FetchUrlResponse;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */

public class CompanyProfilePresenter extends AbstractActivity {


  private ActionDispatcherServiceAsync service;
  private CompanyProfileView view;
  private SecuredActionBuilder securedActionBuilder;
  private SecurityTokenProvider provider;

  @Inject
  public CompanyProfilePresenter(ActionDispatcherServiceAsync service, CompanyProfileView view,
                                 SecuredActionBuilder securedActionBuilder, SecurityTokenProvider provider) {
    this.service = service;

    this.view = view;

    this.securedActionBuilder = securedActionBuilder;
    this.provider = provider;
  }

  public void fetchCompanyProfile() {
    SecuredAction<SecuredResponse<FetchCompanyResponse>> action =
            securedActionBuilder.build(new FetchCompanyAction(provider.getToken().getUser()));
    GotResponse<SecuredResponse<FetchCompanyResponse>> response = new GotResponse<SecuredResponse<FetchCompanyResponse>>() {
      @Override
      public void gotResponse(SecuredResponse<FetchCompanyResponse> result) {
        view.showCompanyProfile(result.getResponse().getCompany());
      }
    };
    service.dispatch(action, response);
  }


  public void fetchUploadURL() {

    service.dispatch(new FetchURLAction(), new GotResponse<FetchUrlResponse>() {
      @Override
      public void gotResponse(FetchUrlResponse result) {
        view.initializeUploadForm(result.getUrl());
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    fetchCompanyProfile();
    panel.setWidget(view);
  }

  public void updateCompany(Company company) {
    view.disableEditButton();
    SecuredAction<SecuredResponse<EditCompanyResponse>> securedAction = securedActionBuilder.build(new EditCompanyAction(company));
    service.dispatch(securedAction, new GotResponse<SecuredResponse<EditCompanyResponse>>() {
      @Override
      public void gotResponse(SecuredResponse<EditCompanyResponse> result) {
        view.showCompanyProfile(result.getResponse().getCompany());
        view.enableEditButton();
      }
    });

  }
}
