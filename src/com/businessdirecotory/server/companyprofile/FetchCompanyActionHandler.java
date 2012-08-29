package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyActionHandler implements ActionHandler<FetchCompanyAction, FetchCompanyResponse> {

  private final CompaniesRepository companiesRepository;

  @Inject
  public FetchCompanyActionHandler(CompaniesRepository companiesRepository, UserRepository userRepository) {
    this.companiesRepository = companiesRepository;
  }

  @Override
  public FetchCompanyResponse handle(FetchCompanyAction action) {
    Company company = null;
    Long userId = action.getId();
    company = companiesRepository.getByUserId(userId);
    if (company == null) {
      company = companiesRepository.getById(action.getId());
    }
    return new FetchCompanyResponse(company);
  }
}
