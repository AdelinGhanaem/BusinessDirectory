package com.businessdirecotory.client.navigation.activites;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenterImpl;
import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationView;
import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationViewImpl;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationActivity extends AbstractActivity {

  @Inject
  private CompanyRegistrationPresenterImpl presenter;

  @Inject
  private CompanyRegistrationView view;

  @Inject
  public CompanyRegistrationActivity(CompanyRegistrationView view) {
    this.view = view;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(presenter);
    view.show();
  }

}
