package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.*;
import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.server.companyregistration.CompaniesRepositoryImpl;
import com.businessdirecotory.server.companyregistration.CompanyEntity;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompaniesRepositoryImplTest extends com.businessdirecotory.AppEngineTestCase {

  private CompaniesRepositoryImpl repository;

  private CompanyBuilder companyBuilder = new CompanyBuilder();

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();

  Key key;


//  private Key key;

  public CompaniesRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
    repository = new CompaniesRepositoryImpl(service);
  }

  @Test
  public void saveCompanyEntity() throws Exception {
    Entity parent = new Entity("Account");
    key = parent.getKey();
    String companyName = "Clouway";
    String companyAddress = "BG25";
    String companyLocation = "Veliko Tarnovo";
    String companyEmail = "mail@mail.com";
    String companyActivity = "activity";
    String companyDescription = "Company Description";
    String companyPhoneNumber = "PhoneNumber";
    String companyContactFace = "Face";
    String password = "password";

    Company company = companyBuilder.withName(companyName).withAddress(companyAddress).withActivity(companyActivity)
            .withContactFace(companyContactFace).withDescription(companyDescription).withPhoneNumber(companyPhoneNumber)
            .withLocation(companyLocation).withEmail(companyEmail).withPassword(password).build();

    repository.save(company);

    Query query = new Query(CompanyEntity.KIND);

    query.setFilter(new Query.FilterPredicate(CompanyEntity.NAME, Query.FilterOperator.EQUAL, companyName));
    Entity entity = service.prepare(query).asSingleEntity();
    assertThat(entity, is(notNullValue()));
    assertThat(entity.getKind(), is(equalTo(CompanyEntity.KIND)));
    assertThat((String) entity.getProperty(CompanyEntity.NAME), is(equalTo(companyName)));
    assertThat((String) entity.getProperty(CompanyEntity.ADDRESS), is(equalTo(companyAddress)));
    assertThat((String) entity.getProperty(CompanyEntity.ACTIVITY), is(equalTo(companyActivity)));
    assertThat((String) entity.getProperty(CompanyEntity.LOCATION), is(equalTo(companyLocation)));
    assertThat((String) entity.getProperty(CompanyEntity.EMAIL), is(equalTo(companyEmail)));
    assertThat((String) entity.getProperty(CompanyEntity.PASSWORD), is(equalTo(password)));

  }

  @Test
  public void multipleCompaniesAreSaved() {

  }

  @Test
  public void returnsTrueWhenCompanyIsNotRegistered() {
    Entity entity = new Entity("Company");
    String email = "mail";
    String password = "password";
    entity.setProperty(CompanyEntity.EMAIL, email);
    entity.setProperty(CompanyEntity.PASSWORD, password);
    service.put(entity);
    Boolean isRegistered = repository.isRegistered(new Account(email, password));
    assertThat(isRegistered,is(equalTo(true)));
  }

  @Test
  public void returnsFalseWhenCompanyIsNotRegistered() throws Exception {
    Entity entity = new Entity("Company");
    String email = "mail";
    String password = "password";
    entity.setProperty(CompanyEntity.EMAIL, email);
    entity.setProperty(CompanyEntity.PASSWORD, password);
    service.put(entity);
    Boolean isRegistered = repository.isRegistered(new Account(email, password));
    assertThat(isRegistered,is(equalTo(true)));
  }
}
