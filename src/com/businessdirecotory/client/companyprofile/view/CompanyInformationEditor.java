package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.shared.entites.CompanyInformation;
import com.github.gwtbootstrap.client.ui.TextBox;
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
  TextBox companyName;
  @UiField
  TextBox contactFace;
  @UiField
  TextBox phoneNumber;
  @UiField
  TextBox email;

  public CompanyInformationEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }
}