package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyActionHandler implements ActionHandler<EditCompanyAction, EditCompanyResponse> {

  private CompaniesRepository repository;

  @Inject
  public EditCompanyActionHandler(CompaniesRepository repository) {
    this.repository = repository;
  }

  @Override
  public EditCompanyResponse handle(EditCompanyAction action) {
    repository.save(action.getCompany());
    Company newCompany = repository.getById(action.getCompany().getId());
    return new EditCompanyResponse(newCompany);

  }
}
