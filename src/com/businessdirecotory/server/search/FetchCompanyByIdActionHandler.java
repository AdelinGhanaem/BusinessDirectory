package com.businessdirecotory.server.search;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyByIdAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyByIdResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyByIdActionHandler implements ActionHandler<FetchCompanyByIdAction, FetchCompanyByIdResponse> {

  private final CompaniesRepository companiesRepository;

  @Inject
  public FetchCompanyByIdActionHandler(CompaniesRepository companiesRepository, UserRepository userRepository) {
    this.companiesRepository = companiesRepository;
  }

  @Override
  public FetchCompanyByIdResponse handle(FetchCompanyByIdAction byIdAction) {
    Company company = null;
    Long id = byIdAction.getId();
    company = companiesRepository.getById(id);
    if (company == null) {
      company = companiesRepository.getByUserId(id);
    }
    return new FetchCompanyByIdResponse(company);
  }
}
