package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.Company;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyValidatorImpl implements CompanyValidator {


  private CompanyValidationErrorMessages errorMessages;

  @Inject
  public CompanyValidatorImpl(CompanyValidationErrorMessages errorMessages) {
    this.errorMessages = errorMessages;
  }

  @Override
  public List<String> validate(Company company) {

    List<String> errors = new ArrayList<String>();
//    if (!isFieldValid(company.getName(), "^[а-яА-Яa-zA-Z0-9]{3,15}$")) {
//      errors.add(errorMessages.invalidCompanyName());
//    }
    if (!isFieldValid(company.getEmail(), "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
      errors.add(errorMessages.invalidEmailForm());
    }

//    if (!isFieldValid(company.getContactFace(), "[а-яА-ЯA-Za-z]{4,}")) {
//      errors.add(errorMessages.invalidContactFace());
//    }
//    if (!isFieldValid(company.getPhoneNumber(), "[0-9]{4,}")) {
//      errors.add(errorMessages.invalidPhoneNumber());
//    }
//    if (company.getPassword().length() < 6) {
//      errors.add(errorMessages.invalidPassword());
//
//    }
    return errors;
  }

  private boolean isFieldValid(String field, String regex) {
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(field).matches();
  }

}
