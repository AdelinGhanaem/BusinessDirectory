package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.navigation.places.CompanyRegistrationPlace;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class NavigationBar extends Composite {

  interface NavigationBarUiBinder extends UiBinder<FlowPanel, NavigationBar> {
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

  public NavigationBar() {
    rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }

  @Override
  public Widget asWidget() {
    return rootElement;
  }

  @UiHandler("registration")
  public void onClick(ClickEvent event) {
//    Window.alert("haha!");
    placeController.goTo(new CompanyRegistrationPlace());
  }




}