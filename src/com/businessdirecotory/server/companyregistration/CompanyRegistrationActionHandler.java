package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationActionHandler implements ActionHandler<CompanyRegistrationAction, UserRegistrationResponse> {

  private CompaniesRepository repository;

  private CompanyValidator validator;

  @Inject
  public CompanyRegistrationActionHandler(CompaniesRepository repository, CompanyValidator validator) {
    this.repository = repository;
    this.validator = validator;
  }


  @Override
  public UserRegistrationResponse handle(CompanyRegistrationAction action) {
    Company company = action.getCompany();

    List<String> list = validator.validate(company);

    if (repository.getByEmail(company.getEmail()) != null) {
      list.add(company.getEmail() + " е вече използван");
    } else {
      if (list.size() == 0) {
        repository.save(action.getCompany());
      }
    }
    return new UserRegistrationResponse(new ArrayList<String>(list));

  }
}
