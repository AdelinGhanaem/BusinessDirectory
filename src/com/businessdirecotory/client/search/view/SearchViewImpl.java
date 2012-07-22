package com.businessdirecotory.client.search.view;

import com.businessdirecotory.client.search.SearchPresenter;
import com.businessdirecotory.client.search.SearchView;
import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchViewImpl implements SearchView {


  interface SearchViewImplUiBinder extends UiBinder<HTMLPanel, SearchViewImpl> {
  }

  private static SearchViewImplUiBinder ourUiBinder = GWT.create(SearchViewImplUiBinder.class);


  HTMLPanel rootElement;

  @UiField(provided = true)
  CellList<Company> companiesList;

  @UiField(provided = true)
  Image loadingImage;

  @UiField
  Button search;
  @UiField
  TextBox searchTexBox;

  private SearchPresenter presenter;

  public SearchViewImpl() {

//    ImagesResource resource = GWT.create(ImagesResource.class);

//    Map<String, String> map = new HashMap<String, String>();
//    map.put("email", resource.email().getUrl());

    CompanyCell companyCell = new CompanyCell();


    companiesList = new CellList<Company>(companyCell);

    loadingImage = new Image("/resources/images/loading.gif");

    loadingImage.setVisible(false);

    rootElement = ourUiBinder.createAndBindUi(this);
  }

  @Override
  public void showSearchResults(ArrayList<Company> companies) {
    companiesList.setRowCount(companies.size());
    companiesList.setRowData(companies);
  }

  @Override
  public void notifyOfEmptyResult() {

  }

  @Override
  public void showLoadingIcon() {
    loadingImage.setVisible(true);
  }

  @Override
  public void hideLoadingIcon() {
    loadingImage.setVisible(false);
  }

  @Override
  public Widget asWidget() {
    return rootElement;
  }

  public void setPresenter(SearchPresenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("search")
  public void onClick(ClickEvent clickEvent) {
    presenter.search(searchTexBox.getText());
  }
}