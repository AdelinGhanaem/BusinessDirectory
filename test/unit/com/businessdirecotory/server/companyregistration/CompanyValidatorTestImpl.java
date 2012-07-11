package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyValidatorTestImpl {

  private CompanyBuilder companyBuilder = new CompanyBuilder();

  @Mock
  private CompanyValidationErrorMessages errorMessages;

  private CompanyValidatorImpl validator;

  private Company validCompany = companyBuilder.build();

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    validator = new CompanyValidatorImpl(errorMessages);
  }

  @Test
  public void returnsEmptyListOfErrorsWhenCompanyIsValid() {
    List<String> errors = validator.validate(validCompany);
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(equalTo(0)));
  }

  @Test
  public void returnsCompanyInvalidNameErrorWhenCompanyNameIsNotBetweenFourToFiveChars() {
//    Company withNoName = companyBuilder.build();
//    withNoName.setName("");
//    String errorMessage = "error";
//    when(errorMessages.invalidCompanyName()).thenReturn(errorMessage);
//    List<String> errors = validator.validate(withNoName);
//    verify(errorMessages).invalidCompanyName();
//    assertThat(errors, is(notNullValue()));
//    assertThat(errors.size(), is(1));
//    assertThat(errors.get(0), is(equalTo(errorMessage)));
  }

  @Test
  public void returnsCompanyEmailInvalidWhenEmailFormIsInvalid() {
    Company withInvalidMail = companyBuilder.build();
    withInvalidMail.setEmail("bla bla bla");
    String error = "bula bula";
    when(errorMessages.invalidEmailForm()).thenReturn(error);
    List<String> errors = validator.validate(withInvalidMail);
    verify(errorMessages).invalidEmailForm();
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(1));
    assertThat(errors.get(0), is(equalTo(error)));
  }


  @Test
  public void returnsInvalidContactFaceWhenContactFaceIsLessThanTheeChars() {
    Company withInvalidMail = companyBuilder.build();
    withInvalidMail.setContactFace("bl");
    String error = ".I.";
    when(errorMessages.invalidContactFace()).thenReturn(error);
    List<String> errors = validator.validate(withInvalidMail);
    verify(errorMessages).invalidContactFace();
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(1));
    assertThat(errors.get(0), is(equalTo(error)));
  }

  @Test
  public void returnsInvalidPhoneNumberWhenAlphabetsPresentsInPhoneNumber() {
    Company withInvalidMail = companyBuilder.build();
    withInvalidMail.setPhoneNumber("0ASD");
    String error = ".I.";
    when(errorMessages.invalidPhoneNumber()).thenReturn(error);
    List<String> errors = validator.validate(withInvalidMail);
    verify(errorMessages).invalidPhoneNumber();
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(1));
    assertThat(errors.get(0), is(equalTo(error)));
  }

  @Test
  public void returnsInvalidPasswordWhenPasswordIsLessThanSixChars() {
    Company withInvalidMail = companyBuilder.build();
    withInvalidMail.setPassword("pass");
    String error = ".I.";
    when(errorMessages.invalidPassword()).thenReturn(error);
    List<String> errors = validator.validate(withInvalidMail);
    verify(errorMessages).invalidPassword();
    assertThat(errors, is(notNullValue()));
    assertThat(errors.size(), is(1));
    assertThat(errors.get(0), is(equalTo(error)));

  }


}
