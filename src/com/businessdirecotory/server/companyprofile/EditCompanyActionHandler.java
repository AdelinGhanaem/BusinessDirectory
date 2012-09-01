package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;

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
    Company current = repository.getByUserId(action.getUserId());
    if (current == null) {
      repository.add(action.getCompany());
    } else {
      repository.update(action.getCompany());
      current = repository.getByUserId(action.getUserId());
    }
    return new EditCompanyResponse(current, new ArrayList<String>());
  }
}
