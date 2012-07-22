package com.businessdirecotory.server.search;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchActionHandler implements ActionHandler<SearchAction, SearchResponse> {

  private CompaniesRepository repository;

  @Inject
  public SearchActionHandler(CompaniesRepository repository) {

    this.repository = repository;
  }

  @Override
  public SearchResponse handle(SearchAction action) {
//    List<Company> companyList = repository.getByKeyWord(action.getKeyWord());
    ArrayList<Company> companyList = new ArrayList<Company>();
    CompanyBuilder builder = new CompanyBuilder();
    for (int i = 0; i < 10; i++) {
      companyList.add(builder.build());
    }
    return new SearchResponse(new ArrayList<Company>(companyList));

  }
}
