package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.impl.EntityCodex;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompaniesRepositoryImpl implements CompaniesRepository {

  private DatastoreService service;

  @Inject
  public CompaniesRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  @Override
  public void save(Company company) {

    Entity companyEntity = new Entity(CompanyEntity.KIND);
    companyEntity.setProperty(CompanyEntity.NAME, company.getName());
    companyEntity.setProperty(CompanyEntity.ADDRESS, company.getAddress());
    companyEntity.setProperty(CompanyEntity.ACTIVITY, company.getActivity());
    companyEntity.setProperty(CompanyEntity.LOCATION, company.getLocation());
    companyEntity.setProperty(CompanyEntity.EMAIL, company.getEmail());
    service.put(companyEntity);
  }
}
