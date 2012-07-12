package com.businessdirecotory.server.search;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import com.evo.gad.shared.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchActionHandlerTest {


  @Mock
  CompaniesRepository repository;

  @Before
  public void setUp() throws Exception {
    initMocks(this);

  }

  @Test
  public void returnsResponseContainsListOfCompaniesMatchingTheSearch() {
    SearchActionHandler handler = new SearchActionHandler(repository);
    ArrayList<Company> companies = new ArrayList<Company>();
    companies.add(new Company());
    companies.add(new Company());
    when(repository.getByKeyWord("abc")).thenReturn(companies);
    SearchResponse response = handler.handle(new SearchAction("abc"));
    verify(repository).getByKeyWord("abc");
    assertThat(response, is(notNullValue()));
    assertThat(response.getCompanies(), is(notNullValue()));
    assertThat(response.getCompanies().size(), is(equalTo(2)));
  }


  @Test
  public void anEmptyListIsReturnedWhenSearchResultIsEmpty() {

    SearchActionHandler handler = new SearchActionHandler(repository);

    ArrayList<Company> companies = new ArrayList<Company>();

    when(repository.getByKeyWord("abc")).thenReturn(companies);

    SearchResponse response = handler.handle(new SearchAction("abc"));

    verify(repository).getByKeyWord("abc");

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompanies(), is(notNullValue()));

    assertThat(response.getCompanies().size(), is(equalTo(0)));
  }


}
