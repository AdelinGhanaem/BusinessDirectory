package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.businessdirecotory.shared.entites.actions.EditCompanyAction;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyActionHandlerTest {

  @Mock
  private CompaniesRepository repository;

  @Mock
  UserRepository userRepository;

  private EditCompanyActionHandler handler;
  CompanyBuilder companyBuilder = new CompanyBuilder();

  @Mock
  private CompanyValidator validator;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    handler = new EditCompanyActionHandler(repository, userRepository, validator);

  }

  @Test
  public void savesCompanyAndReturnsTheNewSavedCompany() {
    Company company = companyBuilder.build();
    company.setActivity("oldActivity");
    company.setId(1l);
    Company newCompany = companyBuilder.build();
    newCompany.setActivity("newActivity");
    newCompany.setId(1l);
    when(repository.add(company)).thenReturn(1l);
    when(repository.getById(1l)).thenReturn(newCompany);
    EditCompanyResponse response = handler.handle(new EditCompanyAction(2l, company));
    assertThat(response, is(notNullValue()));
    assertThat(response.getCompany(), is(notNullValue()));
    assertThat(response.getCompany().getActivity(), is(equalTo("newActivity")));
    assertThat(response.getCompany().getId(), is(equalTo(1l)));
  }


  @Test
  public void companyValidation() {
    Company company = companyBuilder.build();
    company.setActivity("oldActivity");
    company.setId(1l);
    when(validator.validate(company)).thenReturn(new ArrayList<String>() {{
      add("error");
    }});
    EditCompanyResponse response = handler.handle(new EditCompanyAction(2l, company));
    verify(validator).validate(company);
    verify(repository, never()).add(company);
    assertThat(response, is(notNullValue()));
    assertThat(response.getCompany(), is(notNullValue()));
    assertThat(response.getErrors(), is(notNullValue()));
    assertThat(response.getErrors().size(), is(1));
  }
}
