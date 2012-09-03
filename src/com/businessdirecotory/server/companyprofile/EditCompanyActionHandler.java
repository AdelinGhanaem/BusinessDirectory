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

  private final CompanyIndexer indexer;

  @Inject
  public EditCompanyActionHandler(CompaniesRepository repository,
                                  UserRepository userRepository,
                                  CompanyValidator validator,
                                  CompanyIndexer indexer) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.validator = validator;
    this.indexer = indexer;
  }

  @Override
  public EditCompanyResponse handle(EditCompanyAction action) {

    Company newCompany = action.getCompany();

    newCompany.setKeyWords(indexer.getKeywords(newCompany));

    Company current = repository.getByUserId(action.getUserId());

    if (current == null) {
      repository.add(newCompany);
    } else {
      repository.update(newCompany);
    }
    current = repository.getByUserId(newCompany.getUserId());
    return new EditCompanyResponse(current, new ArrayList<String>());
  }
}
