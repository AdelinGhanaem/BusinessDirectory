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

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
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
  public void addCompanyIfNotPreviousCompany() {
    Company company = companyBuilder.build();

    long userId = 1l;

    when(repository.getByUserId(userId)).thenReturn(null);

    EditCompanyResponse response = handler.handle(new EditCompanyAction(userId, company));

    assertThat(response, is(notNullValue()));

    verify(repository).getByUserId(userId);

    verify(repository).add(company);
  }


  @Test
  public void updatesCompanyIfCompanyPreviouslyExists() {
    Company company = companyBuilder.build();

    long userId = 1l;

    when(repository.getByUserId(userId)).thenReturn(company);

    EditCompanyResponse response = handler.handle(new EditCompanyAction(userId, company));

    assertThat(response, is(notNullValue()));

    verify(repository).getByUserId(userId);

    verify(repository).update(company);
  }


  @Test
  public void companyValidation() {
//    Company company = companyBuilder.build();
//    company.setActivity("oldActivity");
//    company.setId(1l);
//    when(validator.validate(company)).thenReturn(new ArrayList<String>() {{
//      add("error");
//    }});
//    EditCompanyResponse response = handler.handle(new EditCompanyAction(2l, company));
//    verify(validator).validate(company);
//    verify(repository, never()).add(company);
//    assertThat(response, is(notNullValue()));
//    assertThat(response.getCompany(), is(notNullValue()));
//    assertThat(response.getErrors(), is(notNullValue()));
//    assertThat(response.getErrors().size(), is(1));
    fail("Implement me ....");

  }
}
