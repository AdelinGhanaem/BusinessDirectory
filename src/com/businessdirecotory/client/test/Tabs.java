package com.businessdirecotory.client.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Tabs extends Composite {
  interface TabsUiBinder extends UiBinder<HTMLPanel, Tabs> {
  }

  private static TabsUiBinder ourUiBinder = GWT.create(TabsUiBinder.class);

  public Tabs() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }
}