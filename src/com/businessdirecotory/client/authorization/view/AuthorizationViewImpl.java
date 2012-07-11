package com.businessdirecotory.client.authorization.view;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.client.authorization.AuthorizationPresenter;
import com.businessdirecotory.client.navigation.InjectableActivityManager;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.event.HideEvent;
import com.github.gwtbootstrap.client.ui.event.HideHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationViewImpl implements AuthorizationView {


  interface AuthorizationViewImplUiBinder extends UiBinder<Modal, AuthorizationViewImpl> {
  }

  private static AuthorizationViewImplUiBinder ourUiBinder = GWT.create(AuthorizationViewImplUiBinder.class);
  @UiField
  Modal loginModal;
  @UiField
  Button login;
  @UiField
  Label messagesLabel;

  @UiField
  TextBox username;

  @UiField
  com.github.gwtbootstrap.client.ui.PasswordTextBox password;

  private AuthorizationPresenter presenter;
  @Inject
  InjectablePlaceController placeController;

  public AuthorizationViewImpl() {

    Modal rootElement = ourUiBinder.createAndBindUi(this);

    messagesLabel.setVisible(false);

    loginModal.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent hideEvent) {
        placeController.goTo(new SearchPlace());
      }
    });

  }

  @Override
  public void hide() {
    loginModal.hide();
  }

  @Override
  public void notifyUserOfWrongUsernameOrPassword() {
    messagesLabel.setVisible(true);
    messagesLabel.setText("Грешно потребителско име или парола");

  }

  @Override
  public void show() {
    loginModal.show();
  }


  @Override
  public void setPresenter(AuthorizationPresenter authorizationPresenter) {
    this.presenter = authorizationPresenter;
  }

  @UiHandler("login")
  public void onLoginClick(ClickEvent event) {
    Account account = new Account(username.getText(), password.getText());
    presenter.authorize(account);
  }


}