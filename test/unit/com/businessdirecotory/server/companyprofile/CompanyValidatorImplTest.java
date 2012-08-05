package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyValidatorImplTest {

  CompanyBuilder companyBuilder = new CompanyBuilder();

  CompanyValidatorImpl companyValidator;

  @Before
  public void setUp() throws Exception {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    companyValidator = new CompanyValidatorImpl(validator);

  }

  @Test
  public void returnsErrorIfCompanyNameIsEmpty() {
    Company company = companyBuilder.build();
    company.setName("");
    List<String> errors = companyValidator.validate(company);
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(1));
    assertThat(errors.get(0), is(equalTo("Запишете името на команията")));
  }


  @Test
  public void returnErrorsIfCompanyLocationIsEmpty() {
    Company company = companyBuilder.build();
    company.setLocation("");
    List<String> errors = companyValidator.validate(company);
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(1));
    assertThat(errors.get(0), is(equalTo("Изберете Град")));
  }
}
