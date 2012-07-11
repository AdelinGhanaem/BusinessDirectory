package com.businessdirecotory.client;

import com.businessdirecotory.client.navigation.WidgetsContainer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class WidgetsContainerImpl implements WidgetsContainer {


  @Override
  public void addToMenu(Widget w) {
    RootPanel navigationMenu = RootPanel.get("navigation");
    navigationMenu.clear();
    navigationMenu.add(w);
  }
}
