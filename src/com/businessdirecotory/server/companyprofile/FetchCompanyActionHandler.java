package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyActionHandler implements ActionHandler<FetchCompanyAction, FetchCompanyResponse> {

  private CompaniesRepository repository;

  @Inject
  public FetchCompanyActionHandler(CompaniesRepository repository) {
    this.repository = repository;
  }

  @Override
  public FetchCompanyResponse handle(FetchCompanyAction action) {
    Company company = repository.getByEmail(action.getCompanyEmail());
    return new FetchCompanyResponse(company);
  }
}
