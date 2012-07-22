package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyActionHandlerTest {


  @Mock
  private CompaniesRepository repository;

  private EditCompanyActionHandler handler;
  CompanyBuilder companyBuilder = new CompanyBuilder();

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new EditCompanyActionHandler(repository);

  }

  @Test
  public void savesCompanyAndReturnsTheNewSavedCompany() {
    Company company = companyBuilder.build();
    company.setActivity("oldActivity");
    company.setId(1l);

    Company newCompany = companyBuilder.build();
    newCompany.setActivity("newActivity");
    newCompany.setId(1l);

    when(repository.getById(1l)).thenReturn(newCompany);

    EditCompanyResponse response = handler.handle(new EditCompanyAction(company));

    assertThat(response, is(notNullValue()));
    assertThat(response.getCompany(), is(notNullValue()));
    assertThat(response.getCompany().getActivity(), is(equalTo("newActivity")));
    assertThat(response.getCompany().getId(), is(equalTo(1l)));

  }
}
