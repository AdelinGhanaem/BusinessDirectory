package com.businessdirecotory.client.search;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.client.search.companyprofileview.CompanyFullInfoViewImpl;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyFullInfoPresenter extends AbstractActivity {


  private ActionDispatcherServiceAsync service;
  private CompanyFullInfoView view;

  @Inject
  public CompanyFullInfoPresenter(ActionDispatcherServiceAsync service, CompanyFullInfoView view) {
    this.service = service;

    this.view = view;
  }

  public void fetchFullInfo(long companyId) {
    service.dispatch(new FetchCompanyAction(companyId), new GotResponse<FetchCompanyResponse>() {
      @Override
      public void gotResponse(FetchCompanyResponse result) {
        if (result.getCompany() != null) {
          view.viewCompany(result.getCompany());
        } else {
          view.notifyUserWhenCompanyIsNull();
        }
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget((CompanyFullInfoViewImpl) view);
  }
}
