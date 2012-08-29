package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.User;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserValidatorTestImpl {

  UserValidatorImpl userValidator;

  @Before
  public void setUp() throws Exception {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    userValidator = new UserValidatorImpl(validator);
  }

  @Test
  public void validatesInvalidEmailForm() {
    String invalidEmail = "invalid";
    List<String> list = userValidator.validate(new User(1l, invalidEmail, "12341234"));
    assertThat(list, is(notNullValue()));
    assertThat(list.size(), is(1));
    assertThat(list.get(0), is(equalTo("Не валидна форма на e-mail: " )));
  }

  @Test
  public void validatesEmptyEmail() {
    String invalidEmail = "";
    List<String> list = userValidator.validate(new User(1l, invalidEmail, "123456"));
    assertThat(list, is(notNullValue()));
    assertThat(list.size(), is(1));
    assertThat(list.get(0), is(equalTo("Празен e-mail !")));
  }

  @Test
  public void passwordCanNotBeLessThanSixChars() {

    String valid = "adelin@mail.com";
    String password = "12345";
    List<String> list = userValidator.validate(new User(1l, valid, password));
    assertThat(list, is(notNullValue()));
    assertThat(list.size(), is(1));
    assertThat(list.get(0), is(equalTo("Парола с по малко от 6 символа е не валидна!")));
  }

}
