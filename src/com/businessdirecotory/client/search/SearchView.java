package com.businessdirecotory.client.search;

import com.businessdirecotory.shared.entites.Company;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface SearchView extends IsWidget {

  void showSearchResults(ArrayList<Company> companies);

  void notifyOfEmptyResult();

  void showLoadingIcon();

  void hideLoadingIcon();

  void setPresenter(SearchPresenter searchPresenter);
}
