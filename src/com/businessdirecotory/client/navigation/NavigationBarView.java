package com.businessdirecotory.client.navigation;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface NavigationBarView extends IsWidget{

  public void showLogoutButton();


  public void hideLoginMenuItem();

  void setStandardMenu();

  void goToNewRegistration();

  void gotToAuthorization();

  void showAuthorizedMenuBar();

  void setPresenter(NavigationBarPresenter navigationBarPresenter);

  interface NavigationBarUiBinder extends UiBinder<FlowPanel, NavigationBarViewImpl> {
  }


  public Widget asWidget();


  public void setUsernameBrand(String username);
}
