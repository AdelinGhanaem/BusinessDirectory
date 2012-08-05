package com.businessdirecotory.client.companyInformation;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.actions.GetCompanyInfoAction;
import com.businessdirecotory.shared.entites.reponses.GetCompanyInfoResponse;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyInfoPresenter {

  private final ActionDispatcherServiceAsync service;
  private final CompanyInfoView view;

  public CompanyInfoPresenter(ActionDispatcherServiceAsync service, CompanyInfoView view) {

    this.service = service;
    this.view = view;
  }

  public void getInfo(Long companyId) {

    service.dispatch(new GetCompanyInfoAction(companyId), new GotResponse<GetCompanyInfoResponse>() {

      @Override
      public void gotResponse(GetCompanyInfoResponse result) {
        if (result.getCompanyInfo() == null) {
          view.notifyUserNoCompanyInfo();
        } else {
          view.showCompanyInfo(result.getCompanyInfo());
        }
      }
    });
  }
}
