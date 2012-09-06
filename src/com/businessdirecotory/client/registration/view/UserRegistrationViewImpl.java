package com.businessdirecotory.client.registration.view;

import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.registration.UserRegistrationPresenter;
import com.businessdirecotory.shared.entites.User;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.PopupImpl;
import com.google.inject.Inject;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationViewImpl extends PopupImpl implements UserRegistrationView {


  interface AccountRegistrationViewImplUiBinder extends UiBinder<Widget, UserRegistrationViewImpl> {
  }

  private Widget rootElement;

  private UserRegistrationPresenter presenter;

  private static AccountRegistrationViewImplUiBinder ourUiBinder = GWT.create(AccountRegistrationViewImplUiBinder.class);

  @UiField
  Modal dialogBox;

  @UiField
  Button registerButton;

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
  @UiField
  Label errorsList;

  @UiField
  Image loadingImage;

  @Inject
  InjectablePlaceController placeController;

  public UserRegistrationViewImpl() {


    Widget rootElement = ourUiBinder.createAndBindUi(this);

    success.setVisible(false);

    close.setVisible(false);


    dialogBox.addHiddenHandler(new HiddenHandler() {
      @Override
      public void onHidden(HiddenEvent hiddenEvent) {
        placeController.goToPreviousPlace();
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
    registerButton.setVisible(false);
    success.setVisible(true);
    close.setVisible(true);
    errorsList.setVisible(false);
  }

  @Override
  public void disableRegistrationButton() {
    registerButton.setVisible(false);
    registerButton.setEnabled(false);
    loadingImage.setVisible(true);
  }

  @Override
  public void enableRegistrationButton() {
    registerButton.setVisible(true  );
    registerButton.setEnabled(true);
    loadingImage.setVisible(false);

  }

  public void setPresenter(UserRegistrationPresenter presenter) {
    this.presenter = presenter;
  }


  @UiHandler("registerButton")
  public void onRegisterClick(ClickEvent event) {
    User user = new User(0l, email.getText(), password.getText());
    presenter.register(user);
  }

  @UiHandler("close")
  public void onCloseClick(ClickEvent event) {
    dialogBox.hide();
  }

  @Override
  public void showErrors(ArrayList<String> errors) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String error : errors) {
      stringBuilder.append("- " + error + "\n");
    }
    errorsList.setVisible(true);
    errorsList.setText(stringBuilder.toString());
  }


}