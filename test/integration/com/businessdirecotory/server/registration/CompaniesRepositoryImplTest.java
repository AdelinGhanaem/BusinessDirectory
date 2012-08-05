package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
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


  public CompaniesRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
    repository = new CompaniesRepositoryImpl(service, new CompanyEntityMapper());
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
    Company company = companyBuilder.withName(companyName).withAddress(companyAddress).withActivity(companyActivity)
            .withContactFace(companyContactFace).withDescription(companyDescription).withPhoneNumber(companyPhoneNumber)
            .withLocation(companyLocation).withEmail(companyEmail).build();

    repository.add(company);

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
  }


  @Test
  public void shouldReturnsCompanyWhenCompanyMatchesSearchKeyWord() {
    Company company = companyBuilder.build();
    company.setActivity(" Software development ");
    repository.add(company);
    List<Company> companies = repository.getByKeyWord("Software");
    assertThat(companies, is(notNullValue()));
    assertThat(companies.size(), is(equalTo(1)));
    assertThat(companies.get(0).getActivity(), is(equalTo(" Software development ")));
  }

  @Test
  public void returnsEmptyListWhenSearchDoesNotMatchAnyCompanyProperties() {
    Company company = companyBuilder.build();
    repository.add(company);
    List<Company> companies = repository.getByKeyWord("Hula Bula");
    assertThat(companies, is(notNullValue()));
    assertThat(companies.size(), is(0));
  }

  @Test
  public void returnsCompaniesIfKeywordMatchesCompaniesProperties() {

    Company company = companyBuilder.withName("Adio OOD").withLocation("NewYork").build();

    Company company2 = companyBuilder.withActivity("Software development").build();

    Company company3 = companyBuilder.build();

    repository.add(company);

    repository.add(company2);

    repository.add(company3);

    List<Company> companyList = repository.getByKeyWord(" Adio  development");

    assertThat(companyList, is(notNullValue()));

    assertThat(companyList.size(), is(equalTo(2)));

  }

  @Test
  public void searchMatchesNumber() {
    Company company = companyBuilder.withName("123").withLocation("NewYork").build();

    Company company2 = companyBuilder.withActivity("Software development").build();

    Company company3 = companyBuilder.build();

    repository.add(company);

    repository.add(company2);

    repository.add(company3);

    List<Company> companyList = repository.getByKeyWord("123");

    assertThat(companyList, is(notNullValue()));

    assertThat(companyList.size(), is(equalTo(1)));

    assertThat(companyList.get(0).getLocation(), is(equalTo("NewYork")));
  }

  @Test
  public void returnsCompanyByEmail() {
    String mail = "mail";
    Company company = companyBuilder.withEmail(mail).build();
    repository.add(company);
    Company returnedCompany = repository.getByEmail(mail);
    assertThat(returnedCompany, is(notNullValue()));
    assertThat(returnedCompany.getEmail(), is(equalTo(company.getEmail())));
    assertThat(returnedCompany.getId(), is(notNullValue()));
  }

  @Test
  public void addingCompanyWithExistingIdAltersCompanyInformation() {

    Company company = companyBuilder.build();

    company.setEmail("mail@mail.com");

    repository.add(company);

    Company returnedCompany = repository.getByEmail("mail@mail.com");

    returnedCompany.setActivity("anotherActivity");

    returnedCompany.setContactFace("Adelin");

    repository.add(returnedCompany);

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
    repository.add(company);
    Company returnedCompany = repository.getByEmail("mail@mail.com");
    Company returnedById = repository.getById(returnedCompany.getId());
    assertThat(returnedById, is(notNullValue()));
    assertThat(returnedById.getEmail(), is(equalTo("mail@mail.com")));

  }

  @Test
  public void returnsCompanyByUserId() {
    Company company = new Company();
    company.setUserId(2l);
    repository.add(company);
    Company returnedByUserId = repository.getByUserId(2l);
    assertThat(returnedByUserId, is(notNullValue()));
    assertThat(returnedByUserId.getUserId(), is(2l));

  }


}
