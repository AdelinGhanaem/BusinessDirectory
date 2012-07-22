package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.companyprofile.CompanyProfilePresenter;
import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.FileUpload;
import com.github.gwtbootstrap.client.ui.Tab;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyProfileViewImpl extends Composite implements CompanyProfileView {


  private CompanyProfilePresenter presenter;

  interface CompanyProfileViewImplUiBinder extends UiBinder<HTMLPanel, CompanyProfileViewImpl> {
  }

  private static CompanyProfileViewImplUiBinder ourUiBinder = GWT.create(CompanyProfileViewImplUiBinder.class);

  interface Driver extends SimpleBeanEditorDriver<Company, CompanyEditor> {
  }

  Driver driver = GWT.create(Driver.class);

  @UiField
  CompanyEditor editor;

  @UiField
  TabPanel tabPanel;

  @UiField
  FileUpload fileUpload;

  @UiField
  FormPanel uploadFormPanel;

  @UiField
  Tab logoTab;

  @UiField
  TextBox username;

  @UiField
  Button submit;

  @UiField
  Button edit;
  @UiField
  Image loadingImage;
//  @UiField
//  VerticalPanel formContainer;

  @Inject
  private SecurityTokenProvider provider;


  public CompanyProfileViewImpl() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);

//    username = new TextBox();
//
//    fileUpload = new FileUpload();
//
//    uploadFormPanel = new FormPanel();

    driver.initialize(editor);

//    uploadFormPanel.setWidget(formContainer);
//
//    formContainer.add(username);
//
//    formContainer.add(fileUpload);
    logoTab.add(uploadFormPanel);


    uploadFormPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
      @Override
      public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        GWT.log("Result: \n" + event.getResults());
      }
    });


  }

  @Override
  public void showCompanyProfile(Company company) {
    driver.edit(company);

  }

  @Override
  public void setPresenter(CompanyProfilePresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void initializeUploadForm(String url) {
    uploadFormPanel.setAction(url);
    uploadFormPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
    uploadFormPanel.setMethod(FormPanel.METHOD_POST);
    username.setText(provider.getToken().getUser());
    username.setText(provider.getToken().getUser());
    Window.alert(uploadFormPanel.getAction());
  }

  @Override
  public void disableEditButton() {
    edit.setEnabled(false);
    loadingImage.setVisible(true);
  }

  @Override
  public void enableEditButton() {
    edit.setEnabled(true);
    loadingImage.setVisible(false);
  }


  @UiHandler("logoTab")
  public void onClick(ClickEvent event) {
    presenter.fetchUploadURL();
  }


  @UiHandler("submit")
  public void clickOnSubmit(ClickEvent event) {
    Window.alert(username.getText());
    uploadFormPanel.submit();
  }

  @UiHandler("edit")
  public void onEdit(ClickEvent event) {
    Company company = driver.flush();
    presenter.updateCompany(company);
  }
}