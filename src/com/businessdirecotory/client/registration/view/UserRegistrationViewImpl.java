package com.businessdirecotory.client.registration.view;

import com.businessdirecotory.client.registration.UserRegistrationPresenter;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.event.HiddenEvent;
import com.github.gwtbootstrap.client.ui.event.HiddenHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.PopupImpl;
import com.google.inject.Inject;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationViewImpl extends PopupImpl implements UserRegistrationView {

  @Override
  public void showErrors(ArrayList<String> errors) {

  }

//  interface Driver extends SimpleBeanEditorDriver<Company, CompanyEditor> {
//  }

  interface AccountRegistrationViewImplUiBinder extends UiBinder<Widget, UserRegistrationViewImpl> {

  }

  private Widget rootElement;

//  private Driver driver = GWT.create(Driver.class);


  private UserRegistrationPresenter presenter;

  private static AccountRegistrationViewImplUiBinder ourUiBinder = GWT.create(AccountRegistrationViewImplUiBinder.class);

  @UiField
  Modal dialogBox;

//  @UiField
//  CompanyEditor companyEditor;

  @UiField
  Button register;
  @UiField
  HTMLPanel info;
  @UiField
  HTMLPanel success;
  @UiField
  Button close;
  @UiField
  TextBox email;
  @UiField
  PasswordTextBox password;

  @Inject
  InjectablePlaceController placeController;

  public UserRegistrationViewImpl() {


    Widget rootElement = ourUiBinder.createAndBindUi(this);

    Company company = new Company();

    success.setVisible(false);

    close.setVisible(false);

//    driver.initialize(companyEditor);

//    driver.edit(company);


    dialogBox.addHiddenHandler(new HiddenHandler() {
      @Override
      public void onHidden(HiddenEvent hiddenEvent) {
        placeController.goTo(new SearchPlace());
      }
    });
  }


  public Widget asWidget() {
    return rootElement;
  }

  public void show() {
    dialogBox.show();
  }

  @Override
  public void notifyOfAccountCreated() {
    info.setVisible(false);
    password.setVisible(false);
    email.setVisible(false);
    register.setVisible(false);
    success.setVisible(true);
    close.setVisible(true);
  }

  public void setPresenter(UserRegistrationPresenter presenter) {
    this.presenter = presenter;
  }


  @UiHandler("register")
  public void onRegisterClick(ClickEvent event) {

    Company company = new Company();
    company.setEmail(email.getText());
    company.setPassword(password.getText());
    company.setActivity("");
    company.setContactFace("");
    company.setAddress("");
    company.setLocation("");
    company.setDescription("");
    company.setPhoneNumber("");
    company.setName("");
    presenter.register(company);
  }

  @UiHandler("close")
  public void onCloseClick(ClickEvent event) {
    dialogBox.hide();
  }


}