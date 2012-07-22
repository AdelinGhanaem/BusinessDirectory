package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationActionHandlerTest {


  @Mock
  CompaniesRepository repository;

  CompanyBuilder companyBuilder = new CompanyBuilder();

  CompanyRegistrationActionHandler handler;


  @Mock
  CompanyValidator validator;


  @Before
  public void setUp() throws Exception {

    initMocks(this);

    Company company = new Company();

    handler = new CompanyRegistrationActionHandler(repository, validator);

  }


  @Test
  public void savesCompanyInCompaniesRepository() throws Exception {
    Company company = new CompanyBuilder().build();
    ArrayList<String> errorsList = new ArrayList<String>();

    UserRegistrationResponse response = handler.handle(new CompanyRegistrationAction(company));
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(equalTo(0)));
    verify(repository).save(company);
    verify(validator).validate(company);
  }

  @Test
  public void CompanyWithValidationErrosIsNotSaved() {
    Company withNoName = companyBuilder.withName("").build();
    when(validator.validate(withNoName)).thenReturn(new ArrayList<String>() {{
      add("Error");
    }});
    UserRegistrationResponse response = handler.handle(new CompanyRegistrationAction(withNoName));
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(equalTo(1)));
    assertThat(response.getErrors().get(0), is(equalTo("Error")));

    verify(repository, never()).save(withNoName);
    verify(validator).validate(withNoName);

  }


  @Test
  public void companyWithReservedEmailIsNotSaved() {
    String mail = "mail";
    Company company = companyBuilder.withEmail(mail).build();
    CompanyRegistrationAction action = new CompanyRegistrationAction(company);
    when(repository.getByEmail(mail)).thenReturn(company);
    UserRegistrationResponse response = handler.handle(action);
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(equalTo(1)));
    verify(repository, never()).save(company);

  }


}
