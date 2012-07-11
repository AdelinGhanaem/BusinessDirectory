package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
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
    companyEntity.setProperty(CompanyEntity.PASSWORD, company.getPassword());
    service.put(companyEntity);
  }

  @Override
  public boolean isRegistered(Account account) {

    Query query = new Query(CompanyEntity.KIND);

    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate(CompanyEntity.EMAIL, Query.FilterOperator.EQUAL, account.getUsername()),
            new Query.FilterPredicate(CompanyEntity.PASSWORD, Query.FilterOperator.EQUAL, account.getPassword())
    ));

    Entity companyEntity = service.prepare(query).asSingleEntity();

    return companyEntity != null;

  }
}
