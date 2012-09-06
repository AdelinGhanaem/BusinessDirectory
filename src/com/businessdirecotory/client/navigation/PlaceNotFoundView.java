package com.businessdirecotory.client.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class PlaceNotFoundView extends Composite {
  interface PlaceNotFoundViewUiBinder extends UiBinder<HTMLPanel, PlaceNotFoundView> {
  }

  private static PlaceNotFoundViewUiBinder ourUiBinder = GWT.create(PlaceNotFoundViewUiBinder.class);

  public PlaceNotFoundView() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }
}