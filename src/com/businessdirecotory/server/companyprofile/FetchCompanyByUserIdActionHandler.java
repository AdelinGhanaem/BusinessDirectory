package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyByUserIdAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyByUserIdResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchCompanyByUserIdActionHandler implements ActionHandler<FetchCompanyByUserIdAction, FetchCompanyByUserIdResponse> {


  private final CompaniesRepository repository;

  @Inject
  public FetchCompanyByUserIdActionHandler(CompaniesRepository repository) {

    this.repository = repository;
  }

  @Override
  public FetchCompanyByUserIdResponse handle(FetchCompanyByUserIdAction action) {

    Long id = action.getUserId();

    Company company = null;

    if (id != 0) {
      company = repository.getByUserId(id);
    }

    FetchCompanyByUserIdResponse response = new FetchCompanyByUserIdResponse(company);

    return response;
  }
}
