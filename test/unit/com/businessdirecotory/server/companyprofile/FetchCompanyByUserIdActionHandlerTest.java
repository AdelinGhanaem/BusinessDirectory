package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.FetchCompanyByUserIdAction;
import com.businessdirecotory.shared.entites.reponses.FetchCompanyByUserIdResponse;
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
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchCompanyByUserIdActionHandlerTest {

  FetchCompanyByUserIdActionHandler handler;


  @Mock
  CompaniesRepository repository;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    handler = new FetchCompanyByUserIdActionHandler(repository);

  }

  @Test
  public void fetchesCompanyById() {

    when(repository.getByUserId(1l)).thenReturn(new Company());

    FetchCompanyByUserIdResponse response = handler.handle(new FetchCompanyByUserIdAction(1l));

    verify(repository).getByUserId(1l);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(notNullValue()));
  }


  @Test
  public void companyIsNotFetchIfIdIsZero() {

    FetchCompanyByUserIdResponse response = handler.handle(new FetchCompanyByUserIdAction(0));

    verify(repository,never()).getByUserId(0);

    assertThat(response, is(notNullValue()));

    assertThat(response.getCompany(), is(nullValue()));

  }



}
