package com.businessdirecotory.client.search.companyprofileview;

import com.businessdirecotory.client.companyprofile.view.CompanyEditor;
import com.businessdirecotory.client.search.CompanyFullInfoView;
import com.businessdirecotory.shared.entites.Company;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyFullInfoViewImpl extends Composite implements CompanyFullInfoView {


  interface FullInfoViewImplUiBinder extends UiBinder<HTMLPanel, CompanyFullInfoViewImpl> {
  }

  interface Driver extends SimpleBeanEditorDriver<Company, CompanyReadOnlyEditor> {
  }

  CompanyEditor companyEditor;

  Driver driver = GWT.create(Driver.class);

  private static FullInfoViewImplUiBinder ourUiBinder = GWT.create(FullInfoViewImplUiBinder.class);

  @UiField
  CompanyReadOnlyEditor editor;

  public CompanyFullInfoViewImpl() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    driver.initialize(editor);
    initWidget(rootElement);
  }


  @Override
  public void viewCompany(Company company) {
    driver.edit(company);
  }

  @Override
  public void notifyUserWhenCompanyIsNull() {

  }
}