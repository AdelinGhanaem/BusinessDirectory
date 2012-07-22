package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.businessdirecotory.shared.entites.actions.FetchCompanyAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyActionHandlerTest {


  @Mock
  private CompaniesRepository repository;

  private FetchCompanyActionHandler handler;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handler = new FetchCompanyActionHandler(repository);

  }


  @Test
  public void returnsResponseContainsCompany() {

    String email = "email";

    Company company = new CompanyBuilder().build();

    when(repository.getByEmail(email)).thenReturn(company);

    FetchCompanyResponse response = handler.handle(new FetchCompanyAction(email));

    verify(repository).getByEmail(email);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(notNullValue()));

  }

  @Test
  public void returnsResponseWithNullCompanyWhenCompanyIsNotAvailable() {
    String email = "email";

    FetchCompanyResponse response = handler.handle(new FetchCompanyAction(email));

    verify(repository).getByEmail(email);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(nullValue()));
  }

}
