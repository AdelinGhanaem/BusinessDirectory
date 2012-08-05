package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyActionHandler implements ActionHandler<EditCompanyAction, EditCompanyResponse> {

  private CompaniesRepository repository;
  private UserRepository userRepository;
  private CompanyValidator validator;

  @Inject
  public EditCompanyActionHandler(CompaniesRepository repository,
                                  UserRepository userRepository,
                                  CompanyValidator validator) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.validator = validator;
  }

  @Override
  public EditCompanyResponse handle(EditCompanyAction action) {
    List<String> list = validator.validate(action.getCompany());
    if (list.size() > 0) {
      return new EditCompanyResponse(action.getCompany(), new ArrayList<String>(list));
    } else {
      Company company = action.getCompany();
      company.setUserId(action.getUserId());
//      company.setEmail(action.get);
      Long companyId = repository.add(company);
      Company newCompany = repository.getById(companyId);
      return new EditCompanyResponse(newCompany, null);
    }

  }
}
