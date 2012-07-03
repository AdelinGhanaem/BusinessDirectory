package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompaniesRepositoryImplTest extends AppEngineTestCase {

  private CompaniesRepository repository;

  private CompanyBuilder companyBuilder = new CompanyBuilder();

  public CompaniesRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
  }

  @Test
  public void saveCompanyEntity() throws Exception {
    Company company = companyBuilder.build();
    repository.save(company);

    Query query = new Query("Company");


  }
}
