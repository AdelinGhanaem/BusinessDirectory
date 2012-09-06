package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyEditor extends Composite implements Editor<Company> {

  interface CompanyEditorUiBinder extends UiBinder<Fieldset, CompanyEditor> {
  }

  private static CompanyEditorUiBinder ourUiBinder = GWT.create(CompanyEditorUiBinder.class);

  @Ignore
  Long id;

//  @UiField
//  Image image;

  @UiField
  AddressEditor address;

  @UiField
  CompanyInformationEditor info;

  @UiField
  DisclosurePanel disclosureInfoPanel;

  @UiField
  Button showInfo;
  @UiField
  Button showAddress;
  @UiField
  DisclosurePanel disclosureAddressPanel;

//  LeafValueEditor<String> logoURL = new LeafValueEditor<String>() {
//    @Override
//    public void setValue(String value) {
//      image.setUrl(value);
//    }
//
//    @Override
//    public String getValue() {
//      return image.getUrl();
//    }
//  };


  public CompanyEditor() {
    Fieldset rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);

  }

  @UiHandler("showInfo")
  public void onInfoClick(ClickEvent event) {
    if (disclosureInfoPanel.isOpen()) {
      disclosureInfoPanel.setOpen(false);
    } else {
      disclosureInfoPanel.setOpen(true);
    }
  }


  @UiHandler("showAddress")
  public void onAddressClick(ClickEvent event) {
    if (disclosureAddressPanel.isOpen()) {
      disclosureAddressPanel.setOpen(false);
    } else {
      disclosureAddressPanel.setOpen(true);
    }
  }
}
