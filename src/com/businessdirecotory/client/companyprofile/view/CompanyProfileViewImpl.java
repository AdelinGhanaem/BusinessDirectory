package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.companyprofile.CompanyProfilePresenter;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.places.AuthorizationPlace;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Tab;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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

//
//  @UiField
//  FileUpload fileUpload;

  @UiField
  FormPanel uploadFormPanel;

  @UiField
  Tab logoTab;

  @UiField
  TextBox username;

  @UiField
  TextBox tokenId;

  @UiField
  TextBox userId;

  @UiField
  Button submit;

  @UiField
  Button edit;

  @UiField
  Image loadingImage;

  @UiField
  Button createProfile;

  @UiField
  Image logo;


  @Inject
  private SecurityTokenProvider provider;


  @Inject
  InjectablePlaceController controller;


  public CompanyProfileViewImpl() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);

    driver.initialize(editor);

    logoTab.add(uploadFormPanel);

    uploadFormPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
      @Override
      public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        presenter.fetchUploadURL();
        presenter.fetchCompanyProfile();
      }
    });

  }

  @Override
  public void showCompanyProfile(Company company) {
    tabPanel.setVisible(true);
    driver.edit(company);
    submit.setEnabled(true);
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
    username.setText(String.valueOf(provider.getToken().getUser()));
    tokenId.setText(String.valueOf(provider.getToken().getTokenId()));
    userId.setText(String.valueOf(provider.getToken().getUserId()));
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

  @Override
  public void showCreateProfileButton() {
    createProfile.setVisible(true);
    tabPanel.setVisible(false);

  }

  @Override
  public void setURL(String imageURL) {
    logo.setUrl(imageURL);
  }

  @Override
  public void goToLoginPlace() {
    controller.goTo(new AuthorizationPlace());
  }

  @UiHandler("logoTab")
  public void onClick(ClickEvent event) {
    presenter.fetchUploadURL();
  }


  @UiHandler("submit")
  public void clickOnSubmit(ClickEvent event) {
    uploadFormPanel.submit();
  }

  @UiHandler("createProfile")
  public void onCreateProfile(ClickEvent event) {

    createProfile.setVisible(false);

    tabPanel.setVisible(true);

    Company company = new CompanyBuilder().buildEmpty();
    company.setUserId(provider.getToken().getUserId());
    driver.edit(company);
  }

  @UiHandler("edit")
  public void onEdit(ClickEvent event) {
    Company company = driver.flush();
    presenter.updateCompany(company);
  }
}