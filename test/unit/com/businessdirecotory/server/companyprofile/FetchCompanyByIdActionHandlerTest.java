package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.server.search.FetchCompanyByIdActionHandler;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyByIdAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyByIdResponse;
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
public class FetchCompanyByIdActionHandlerTest {


  @Mock
  private CompaniesRepository companiesRepository;

  @Mock
  private UserRepository userRepository;

  private FetchCompanyByIdActionHandler handlerById;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handlerById = new FetchCompanyByIdActionHandler(companiesRepository, userRepository);

  }


//  @Test
//  public void returnsResponseContainsCompanyFetchById() {
//
//    Long companyId = 2l;
//
//    Company company = new CompanyBuilder().build();
//
//
//    FetchCompanyByIdResponse response = handlerById.handle(new FetchCompanyByIdAction(companyId));
//
//    verify(companiesRepository).getByUserId(companyId);
//
//    verify(companiesRepository, never()).getById(companyId);
//
//    assertThat(response, is(notNullValue()));
//
//    assertThat(response.getCompany(), is(notNullValue()));
//
//  }


  @Test
  public void shouldFetchCompanyById() {

    Long userId = 2l;

    when(companiesRepository.getById(userId)).thenReturn(new Company());

    FetchCompanyByIdResponse byIdResponse = handlerById.handle(new FetchCompanyByIdAction(userId));

    verify(companiesRepository).getById(userId);

    assertThat(byIdResponse, is(notNullValue()));

    assertThat(byIdResponse.getCompany(), is(notNullValue()));

  }

  @Test
  public void returnsNullWhenCompanyIsNotFound() {

    Long id = 1l;

    when(userRepository.getById(id)).thenReturn(null);

    when(companiesRepository.getById(id)).thenReturn(null);

    FetchCompanyByIdResponse byIdResponse = handlerById.handle(new FetchCompanyByIdAction(id));

    verify(companiesRepository).getById(id);

    assertThat(byIdResponse, is(notNullValue()));

    assertThat(byIdResponse.getCompany(), is(nullValue()));

  }

}
