package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyActionHandlerTest {


  @Mock
  private CompaniesRepository companiesRepository;

  @Mock
  private UserRepository userRepository;

  private FetchCompanyActionHandler handler;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handler = new FetchCompanyActionHandler(companiesRepository, userRepository);

  }


  @Test
  public void returnsResponseContainsCompany() {

    Long userId = 1l;

    Long companyId = 2l;

    Company company = new CompanyBuilder().build();

    when(companiesRepository.getByUserId(userId)).thenReturn(company);

    FetchCompanyResponse response = handler.handle(new FetchCompanyAction(userId));

    verify(companiesRepository).getByUserId(userId);

    verify(companiesRepository, never()).getById(userId);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(notNullValue()));

  }

  @Test
  public void fetchCompanyByIdIfCompanyByUserIdIsNotAvailable() {

    Long userId = 2l;

    when(companiesRepository.getByUserId(userId)).thenReturn(null);

    when(companiesRepository.getById(userId)).thenReturn(new Company());

    FetchCompanyResponse response = handler.handle(new FetchCompanyAction(userId));

    verify(companiesRepository).getById(userId);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(notNullValue()));

  }

  @Test
  public void returnsNullIfCompanyIsNotFoundByIdOrByUserId() {

    Long id = 1l;

    when(userRepository.getById(id)).thenReturn(null);

    when(companiesRepository.getById(id)).thenReturn(null);

    FetchCompanyResponse response = handler.handle(new FetchCompanyAction(id));

    verify(companiesRepository).getById(id);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(nullValue()));

  }

}
