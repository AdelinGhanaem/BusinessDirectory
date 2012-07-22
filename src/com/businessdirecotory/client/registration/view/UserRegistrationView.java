package com.businessdirecotory.client.registration.view;

import com.businessdirecotory.client.registration.UserRegistrationPresenter;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface UserRegistrationView {

  void showErrors(ArrayList<String> errors);

  void setPresenter(UserRegistrationPresenter presenter);

  void show();

  void notifyOfAccountCreated();
}
