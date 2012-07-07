package com.businessdirecotory.client.companyregistration.view;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenter;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyRegistrationView {

  void showErrors(ArrayList<String> errors);

  void setPresenter(CompanyRegistrationPresenter presenter);

  void show();

  void notifyOfAccountCreated();
}
