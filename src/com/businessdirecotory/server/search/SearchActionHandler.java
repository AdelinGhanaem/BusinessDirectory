package com.businessdirecotory.server.search;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchActionHandler implements ActionHandler<SearchAction, SearchResponse> {

  private CompaniesRepository repository;

  public SearchActionHandler(CompaniesRepository repository) {

    this.repository = repository;
  }

  @Override
  public SearchResponse handle(SearchAction action) {
    List<Company> companyList = repository.getByKeyWord(action.getKeyWord());
    return new SearchResponse(new ArrayList<Company>(companyList));
  }
}
