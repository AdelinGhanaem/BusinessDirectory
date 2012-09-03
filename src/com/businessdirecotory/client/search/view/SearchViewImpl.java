package com.businessdirecotory.client.search.view;

import com.businessdirecotory.client.navigation.InjectablePlaceController;
import com.businessdirecotory.client.navigation.places.FullInfoPlace;
import com.businessdirecotory.client.resources.ImagesResource;
import com.businessdirecotory.client.search.SearchPresenter;
import com.businessdirecotory.client.search.SearchView;
import com.businessdirecotory.shared.entites.Company;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchViewImpl implements SearchView {


  private EventBus eventBus;

  private InjectablePlaceController controller;

  interface SearchViewImplUiBinder extends UiBinder<HTMLPanel, SearchViewImpl> {
  }

  private static SearchViewImplUiBinder ourUiBinder = GWT.create(SearchViewImplUiBinder.class);

  HTMLPanel rootElement;

  @UiField(provided = true)
  CellTable<Company> companiesList;

  @UiField
  Image loadingImage;

  @UiField
  Button search;

  @UiField
  TextBox searchTexBox;

  @UiField
  ScrollPanel scrollPanel;

  @UiField(provided = true)
  SimplePager pager;

  @UiField
  HTMLPanel searchResult;

  @UiField
  HTMLPanel noResultsPanel;

  private SearchPresenter presenter;

  ListDataProvider<Company> companyListDataProvider;

  @Inject
  public SearchViewImpl(final EventBus eventBus, final InjectablePlaceController controller) {
    this.eventBus = eventBus;
    this.controller = controller;

    pager = new SimplePager(SimplePager.TextLocation.CENTER);

    companiesList = new CellTable<Company>(10);

    companiesList.addColumn(new Column<Company, Company>(new CompanyCell(ImagesResource.INSTANCE)) {
      @Override
      public Company getValue(Company object) {
        return object;
      }
    });
    Column<Company, String> viewProfileButton = new Column<Company, String>(new ButtonCell()) {
      @Override
      public String getValue(Company object) {
        return "Подробна информация";
      }
    };

    viewProfileButton.setFieldUpdater(new FieldUpdater<Company, String>() {
      @Override
      public void update(int index, Company object, String value) {
        //TODO:Put split point here  ... !
        controller.goTo(new FullInfoPlace(object.getId()));
      }
    });

    companiesList.addColumn(viewProfileButton);

    pager.setDisplay(companiesList);

    companyListDataProvider = new ListDataProvider<Company>();

    companyListDataProvider.addDataDisplay(companiesList);

    rootElement = ourUiBinder.createAndBindUi(this);

    loadingImage.setVisible(false);

    searchTexBox.addKeyPressHandler(new KeyPressHandler() {
      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (event.getCharCode() == KeyCodes.KEY_ENTER) {
          presenter.search(searchTexBox.getText());
        }
      }
    });

  }

  @Override
  public void showSearchResults(ArrayList<Company> companies) {
    noResultsPanel.setVisible(false);
    searchResult.setVisible(true);
    companyListDataProvider.setList(companies);

  }

  @Override
  public void notifyOfEmptyResult() {
    searchResult.setVisible(false);
    noResultsPanel.setVisible(true);
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