package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationResponse;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.businessdirecotory.shared.entites.actions.CompanyRegistrationAction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationActionHandlerTest {


  @Mock
  CompaniesRepository repository;

  CompanyBuilder companyBuilder = new CompanyBuilder();

  CompanyRegistrationActionHandler handler;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    Company company = new Company();

    handler = new CompanyRegistrationActionHandler(repository);

  }


  @Test
  public void savesCompanyInCompaniesRepository() throws Exception {
    Company company = new CompanyBuilder().build();
    CompanyRegistrationResponse response = handler.handle(new CompanyRegistrationAction(company));
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(equalTo(0)));
    verify(repository).save(company);
  }

  @Test
  public void CompanyWithNoNameIsNotSaved() {
    Company withNoName = companyBuilder.withName("").build();
    CompanyRegistrationResponse response = handler.handle(new CompanyRegistrationAction(withNoName));
    assertThat(response, is(notNullValue()));
    assertThat(response.getErrors(), is(notNull()));
    assertThat(response.getErrors().size(), is(equalTo(1)));
    verify(repository, never()).save(withNoName);
  }


}
