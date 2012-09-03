package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.navigation.places.AuthorizationPlace;
import com.businessdirecotory.client.navigation.places.CompanyProfilePlace;
import com.businessdirecotory.client.navigation.places.UserRegistrationPlace;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class NavigationBarViewImpl extends Composite implements NavigationBarView {


  private NavigationBarPresenter presenter;

  interface NavigationBarUiBinder extends UiBinder<FlowPanel, NavigationBarViewImpl> {
  }

  private static NavigationBarUiBinder ourUiBinder = GWT.create(NavigationBarUiBinder.class);

  FlowPanel rootElement;

  @Inject
  InjectablePlaceController placeController;

  @UiField
  NavLink login;

  @UiField
  NavLink registration;

  @UiField
  NavLink aboutUs;

  @UiField
  Label usernameBrand;

  @UiField
  Button logoutButton;

  @UiField
  NavLink home;

  @Inject
  SecurityTokenProvider provider;


  @Inject
  EventBus eventBus;

  public NavigationBarViewImpl() {
    rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
    usernameBrand.setVisible(false);
    logoutButton.setVisible(false);
  }

  @Override
  public Widget asWidget() {
    return rootElement;
  }


  public void setUsernameBrand(String username) {
    usernameBrand.setVisible(true);
    usernameBrand.setText("Потребител:" + username);
  }

  public void showLogoutButton() {
    logoutButton.setVisible(true);
  }

  public void hideLoginMenuItem() {
    login.setVisible(false);
  }

  @Override
  public void showStandardMenu() {
    login.setVisible(true);
    logoutButton.setVisible(false);
    usernameBrand.setVisible(false);
  }

  @Override
  public void goToNewRegistration() {
    placeController.goTo(new UserRegistrationPlace());
  }

  @Override
  public void gotToAuthorization() {
    placeController.goTo(new AuthorizationPlace());
  }

  @Override
  public void showAuthorizedMenuBar() {
    login.setVisible(false);
    logoutButton.setVisible(true);
  }

  @UiHandler("registration")
  public void onClick(ClickEvent event) {
    presenter.onNewRegistration();
  }


  @UiHandler("login")
  public void onLoginClick(ClickEvent event) {
    presenter.onAuthorization();
  }

  @UiHandler("logoutButton")
  public void onLogout(ClickEvent event) {
    if (logoutButton != null && presenter != null) {
      presenter.logout();
    }
  }

  @UiHandler("usernameBrand")
  public void onEmailClick(ClickEvent event) {
    placeController.goTo(new CompanyProfilePlace());
  }

  @UiHandler("home")
  public void onGoHome(ClickEvent event) {
    placeController.goTo(new SearchPlace());
  }


  public void setPresenter(NavigationBarPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void gotToMainPage() {
    placeController.goTo(new SearchPlace());
    Window.Location.replace("");
  }


}