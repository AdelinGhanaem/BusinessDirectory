package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Address;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.CompanyInformation;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompaniesRepositoryImplTest extends com.businessdirecotory.AppEngineTestCase {

  static public class Person {
    Long id;

    private Person() {
    }
  }

  private CompaniesRepositoryImpl repository;

  private CompanyBuilder companyBuilder = new CompanyBuilder();

  DatastoreService service = DatastoreServiceFactory.getDatastoreService();


  public CompaniesRepositoryImplTest() {
    super(new LocalDatastoreServiceTestConfig());
    repository = new CompaniesRepositoryImpl(new AnnotationObjectDatastore());
  }

  @Test
  public void saveCompanyEntity() throws Exception {
    Company company = new CompanyBuilder().build();

    Long id = repository.add(company);

    Company returned = repository.getById(id);

    assertThat(returned, is(notNullValue()));

  }


  @Test
  public void shouldReturnsCompanyWhenCompanyMatchesSearchKeyWord() {
    String keyword = "software";
    Company company = createCompany(keyword);
    repository.add(company);
    List<Company> matchedCompanies = repository.getByKeyWord(keyword);
    assertThat(matchedCompanies, is(notNullValue()));
    assertThat(matchedCompanies.size(), is(1));
    assertThat(matchedCompanies.get(0).getIndex().contains(keyword), is(true));
  }

  private Company createCompany(String keyword) {
    Company company = companyBuilder.build();
    Address address = new Address();
    address.setDescription("This is a " + keyword + " company...!");
    company.setAddress(address);
    return company;
  }


  @Test
  public void returnsNullWhenNoCompanyForTheGivenId() {

    Company company = repository.getById(1l);

    assertThat(company, is(nullValue()));

  }


//  @Test
//  public void throwsExceptionWhenMoreThanOneCompanyIsReturned() {
//    Company company = createCompany("word");
//    repository.add(company);
//    repository.add(company);
//
//  }

  @Test
  public void returnsEmptyListWhenSearchDoesNotMatchAnyCompanyProperties() {

    Company company = companyBuilder.build();

    repository.add(company);

    List<Company> result = repository.getByKeyWord("Bull SHIT");

    assertThat(result, is(notNullValue()));

    assertThat(result.size(), is(0));

  }


//  @Test
//  public void returnsCompanyByEmail() {
//    String mail = "mail";
//    Company company = companyBuilder.withEmail(mail).build();
//    repository.add(company);
//    Company returnedCompany = repository.getByEmail(mail);
//    assertThat(returnedCompany, is(notNullValue()));
//    assertThat(returnedCompany.getEmail(), is(equalTo(company.getEmail())));
//    assertThat(returnedCompany.getId(), is(notNullValue()));
//    fail("Implement me ....");
//
//  }

  @Test
  public void addingCompanyWithExistingIdAltersCompanyInformation() {

    Company oldCompany = createCompanyWithEmail("mail@mail.com");

    Company newCompany = createCompanyWithEmail("anotherMail@mail.com");

    Long id = repository.add(oldCompany);

    Company current = repository.getById(id);

    newCompany.setId(current.getId());

    repository.update(newCompany);

    Company updatedCompany = repository.getById(id);

    assertThat(updatedCompany.getInfo().getEmail(), is(equalTo("anotherMail@mail.com")));

  }

  private Company createCompanyWithEmail(String email) {
    Company company = companyBuilder.build();

    CompanyInformation information = new CompanyInformation();

    information.setActivity("BullShit");

    information.setEmail(email);
    company.setInfo(information);
    return company;
  }


  @Test
  public void returnsCompanyByUserId() {
    Company company = companyBuilder.build();
    company.setUserId(2l);
    repository.add(company);
    Company returnedByUserId = repository.getByUserId(2l);
    assertThat(returnedByUserId, is(notNullValue()));
    assertThat(returnedByUserId.getUserId(), is(2l));

  }


}
