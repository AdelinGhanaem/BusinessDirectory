package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationResponse;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import com.evo.gad.dispatch.ActionHandler;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationActionHandler implements ActionHandler<CompanyRegistrationAction, CompanyRegistrationResponse> {

  private CompaniesRepository repository;

  public CompanyRegistrationActionHandler(CompaniesRepository repository) {

    this.repository = repository;
  }

  @Override
  public CompanyRegistrationResponse handle(CompanyRegistrationAction action) {
    repository.save(action.getCompany());
    return new CompanyRegistrationResponse(new ArrayList<String>());
  }
}
