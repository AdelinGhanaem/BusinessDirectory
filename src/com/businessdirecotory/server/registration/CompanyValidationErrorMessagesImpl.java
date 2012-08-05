package com.businessdirecotory.server.registration;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyValidationErrorMessagesImpl implements CompanyValidationErrorMessages {
  @Override
  public String invalidCompanyName() {
    return "не валидно име на фирма\n";
  }

  @Override
  public String invalidEmailForm() {
    return "не валидна форма на е-mail,пример mail@mail.com ";
  }

  @Override
  public String invalidContactFace() {
    return "името на лицето за контак трявва да е от поне 4 букви";
  }

  @Override
  public String invalidPhoneNumber() {
    return "не валиден телефонен номер";
  }

  @Override
  public String invalidPassword() {
    return "паролата трябва да е поне 6 символа";
  }
}
