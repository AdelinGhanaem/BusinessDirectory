package com.businessdirecotory.client.search.companyprofileview;

import com.businessdirecotory.shared.entites.CompanyInformation;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyInformationEditor extends Composite implements Editor<CompanyInformation> {

  interface CompanyInformationEditorUiBinder extends UiBinder<HTMLPanel, CompanyInformationEditor> {
  }

  private static CompanyInformationEditorUiBinder ourUiBinder = GWT.create(CompanyInformationEditorUiBinder.class);

  @UiField
  com.google.gwt.user.client.ui.Label companyName;

  @UiField
  com.google.gwt.user.client.ui.Label contactFace;

  @UiField
  com.google.gwt.user.client.ui.Label phoneNumber;

  @UiField
  com.google.gwt.user.client.ui.Label email;

  @UiField
  com.google.gwt.user.client.ui.Label activity;

  @UiField
  com.google.gwt.user.client.ui.Label description;

  public CompanyInformationEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }
}