package com.businessdirecotory.client.navigation;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class PlaceNotFoundActivity extends AbstractActivity {


  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    PlaceNotFoundView view = new PlaceNotFoundView();
    panel.setWidget(view);
  }
}
