package com.businessdirecotory.client.companyregistration.view;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenter;
import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.event.HiddenEvent;
import com.github.gwtbootstrap.client.ui.event.HiddenHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.PopupImpl;
import com.google.inject.Inject;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationViewImpl extends PopupImpl implements CompanyRegistrationView {

  @Override
  public void showErrors(ArrayList<String> errors) {

  }

  interface Driver extends SimpleBeanEditorDriver<Company, CompanyEditor> {
  }

  interface AccountRegistrationViewImplUiBinder extends UiBinder<Widget, CompanyRegistrationViewImpl> {

  }

  private Widget rootElement;

  private Driver driver = GWT.create(Driver.class);


  private CompanyRegistrationPresenter presenter;

  private static AccountRegistrationViewImplUiBinder ourUiBinder = GWT.create(AccountRegistrationViewImplUiBinder.class);

  @UiField
  Modal dialogBox;

  @UiField
  CompanyEditor companyEditor;

  @UiField
  Button register;

  @Inject
  InjectablePlaceController placeController;

  public CompanyRegistrationViewImpl() {


    Widget rootElement = ourUiBinder.createAndBindUi(this);

    Company company = new Company();

    driver.initialize(companyEditor);

    driver.edit(company);

    dialogBox.addHiddenHandler(new HiddenHandler() {
      @Override
      public void onHidden(HiddenEvent hiddenEvent) {
        placeController.goTo(new SearchPlace());
      }
    });


  }


  public Widget asWidget() {
    return rootElement;
  }

  public void show() {
    dialogBox.show();
  }

  public void setPresenter(CompanyRegistrationPresenter presenter) {
    this.presenter = presenter;
  }


  @UiHandler("register")
  public void onClick(ClickEvent event) {
    Company company = driver.flush();
    presenter.register(company);
  }


}