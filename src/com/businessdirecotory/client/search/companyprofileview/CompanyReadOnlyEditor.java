package com.businessdirecotory.client.search.companyprofileview;

import com.businessdirecotory.shared.entites.Company;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyReadOnlyEditor extends Composite implements Editor<Company> {

  interface CompanyReadOnlyEditorUiBinder extends UiBinder<HTMLPanel, CompanyReadOnlyEditor> {
  }

  private static CompanyReadOnlyEditorUiBinder ourUiBinder = GWT.create(CompanyReadOnlyEditorUiBinder.class);


  @Ignore
  Long id;

  @UiField
  Image image;

  @UiField
  AddressEditor address;

  @UiField
  CompanyInformationEditor info;


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

//  private String facebook;
//
//  private String googlePlus;
//
//  private String twitter;

  public CompanyReadOnlyEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);

//    facebookImage.addClickHandler(new ClickHandler() {
//      @Override
//      public void onClick(ClickEvent event) {
//        Window.open(facebook, "_blank", "");
//      }
//    });
//
//    plusImage.addClickHandler(new ClickHandler() {
//      @Override
//      public void onClick(ClickEvent event) {
//        Window.open(googlePlus, "_blank", "");
//      }
//    });
//
//    twitterImage.addClickHandler(new ClickHandler() {
//      @Override
//      public void onClick(ClickEvent event) {
//        Window.open(twitter, "_blank", "");
//      }
//    });

  }
}