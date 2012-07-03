package com.businessdirecotory.client.search;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchPresenter extends AbstractActivity {


  private final ActionDispatcherServiceAsync service;
  private final SearchView view;

  @Inject
  public SearchPresenter(ActionDispatcherServiceAsync service, SearchView view) {
    this.service = service;
    this.view = view;
  }

  public void search(String keyword) {
    view.showLoadingIcon();
    service.dispatch(new SearchAction<SearchResponse>(keyword), new GotResponse<SearchResponse>() {
      @Override
      public void gotResponse(SearchResponse result) {
        view.showSearchResults(result.getCompanies());
        if (result.getCompanies().size() == 0) {
          view.notifyOfEmptyResult();
        }
        view.hideLoadingIcon();
      }
    });
  }


  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    panel.setWidget(view.asWidget());
  }
}
