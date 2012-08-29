package com.businessdirecotory.client.search;

import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static com.businessdirecotory.client.search.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchPresenterTest {

  @Mock
  private SearchView searchView;

  @Mock
  private ActionDispatcherServiceAsync service;

  private SearchPresenter searchPresenter;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    searchPresenter = new SearchPresenter(service, searchView);
  }

  @Test
  public void showsReturnedSearchResult() {
    ArrayList<Company> companies = new ArrayList<Company>();
    companies.add(new Company());
    SearchResponse searchResponse = new SearchResponse(companies);
    doOnSuccess(searchResponse).when(service).dispatch(isA(SearchAction.class), isA(GotResponse.class));
    searchPresenter.search("keyword");
    verify(searchView).showSearchResults(companies);
  }

  @Test
  public void showNoResultNotificationWhenNoResult() {

    ArrayList<Company> companies = new ArrayList<Company>();

    SearchResponse searchResponse = new SearchResponse(companies);

    doOnSuccess(searchResponse).when(service).dispatch(isA(SearchAction.class), isA(GotResponse.class));

    searchPresenter.search("keyword");

    verify(searchView).notifyOfEmptyResult();
  }

  @Test
  public void showsLoadingIconBeforeSearchActionIsSend() {
    searchPresenter.search("keyword");
    verify(searchView).showLoadingIcon();
  }

  @Test
  public void searchWithNoKeyWordsIsNotPerformed() throws Exception {
    searchPresenter.search("");
    verify(service,never()).dispatch(any(SearchAction.class), any(GotResponse.class));
    verify(searchView).notifyOfEmptyResult();

  }

  @Test
  public void hidesLoadingIconWhenResponseIsReturned() {
    SearchResponse searchResponse = new SearchResponse(new ArrayList<Company>());
    doOnSuccess(searchResponse).when(service).dispatch(isA(SearchAction.class), isA(GotResponse.class));
    searchPresenter.search("keyword");
    verify(searchView).hideLoadingIcon();
  }


}
