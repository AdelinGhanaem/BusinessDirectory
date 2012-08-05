package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyEditor extends Composite implements Editor<Company> {

  interface CompanyEditorUiBinder extends UiBinder<Fieldset, CompanyEditor> {
  }

  private static CompanyEditorUiBinder ourUiBinder = GWT.create(CompanyEditorUiBinder.class);

  @UiField
  TextBox name;

  @UiField
  TextBox location;

  @UiField
  TextBox activity;

  @UiField
  TextArea description;

  @UiField
  TextBox phoneNumber;

  @UiField
  TextBox contactFace;

  @UiField
  TextBox address;

  @Ignore
  Long id;

  @UiField
  Image image;

  @UiField
  TextBox facebook;

  @UiField
  TextBox googlePlus;

  @UiField
  TextBox twitter;

  LeafValueEditor<String> logoURL = new LeafValueEditor<String>() {
    @Override
    public void setValue(String value) {
      image.setUrl(value);
    }

    @Override
    public String getValue() {
      return image.getUrl();
    }
  };


  public CompanyEditor() {
    Fieldset rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }

}
