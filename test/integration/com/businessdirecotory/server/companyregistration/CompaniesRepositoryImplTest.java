package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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


  public CompaniesRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
    repository = new CompaniesRepositoryImpl(service);
  }

  @Test
  public void saveCompanyEntity() throws Exception {
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
    assertThat(isRegistered, is(equalTo(true)));
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
    assertThat(isRegistered, is(equalTo(true)));
  }

  @Test
  public void shouldReturnsCompanyWhenCompanyMatchesSearchKeyWord() {
    Company company = companyBuilder.build();
    company.setActivity(" Software development ");
    repository.save(company);
    List<Company> companies = repository.getByKeyWord("Software");
    assertThat(companies, is(notNullValue()));
    assertThat(companies.size(), is(equalTo(1)));
    assertThat(companies.get(0).getActivity(), is(equalTo(" Software development ")));
  }

  @Test
  public void returnsEmptyListWhenSearchDoesNotMatchAnyCompanyProperties() {
    Company company = companyBuilder.build();
    repository.save(company);
    List<Company> companies = repository.getByKeyWord("Hula Bula");
    assertThat(companies, is(notNullValue()));
    assertThat(companies.size(), is(0));
  }

  @Test
  public void returnsCompaniesIfKeywordMatchesCompaniesProperties() {

    Company company = companyBuilder.withName("Adio OOD").withLocation("NewYork").build();

    Company company2 = companyBuilder.withActivity("Software development").build();

    Company company3 = companyBuilder.build();

    repository.save(company);

    repository.save(company2);

    repository.save(company3);

    List<Company> companyList = repository.getByKeyWord(" Adio  development");

    assertThat(companyList, is(notNullValue()));

    assertThat(companyList.size(), is(equalTo(2)));

  }

  @Test
  public void returnsCompanyByEmail() {
    String mail = "mail";
    Company company = companyBuilder.withEmail(mail).build();
    repository.save(company);
    Company returnedCompany = repository.getByEmail(mail);
    assertThat(returnedCompany, is(notNullValue()));
    assertThat(returnedCompany.getEmail(), is(equalTo(company.getEmail())));
    assertThat(returnedCompany.getId(), is(notNullValue()));
  }

  @Test
  public void addingCompanyWithExistingIdAltersCompanyInformation() {

    Company company = companyBuilder.build();

    company.setEmail("mail@mail.com");

    repository.save(company);

    Company returnedCompany = repository.getByEmail("mail@mail.com");

    returnedCompany.setActivity("anotherActivity");
    returnedCompany.setContactFace("Adelin");

    repository.save(returnedCompany);

    Query query = new Query(CompanyEntity.KIND);

    query.setFilter(new Query.FilterPredicate(CompanyEntity.EMAIL, Query.FilterOperator.EQUAL, "mail@mail.com"));
    Iterable<Entity> entities = service.prepare(query).asIterable();
    assertThat(Arrays.asList(entities).size(), is(equalTo(1)));
    assertThat((String) entities.iterator().next().getProperty(CompanyEntity.ACTIVITY), is(equalTo("anotherActivity")));
    assertThat((String) entities.iterator().next().getProperty(CompanyEntity.CONTACT_FACE), is(equalTo("Adelin")));
  }

  @Test
  public void returnsCompanyById() {
    Company company = companyBuilder.build();
    company.setEmail("mail@mail.com");
    repository.save(company);
    Company returnedCompany = repository.getByEmail("mail@mail.com");
    Company returnedById = repository.getById(returnedCompany.getId());
    assertThat(returnedById, is(notNullValue()));
    assertThat(returnedById.getEmail(), is(equalTo("mail@mail.com")));

  }


}
