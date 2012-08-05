package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.Company;
import com.google.inject.Inject;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyValidatorImpl implements CompanyValidator {


  private Validator validator;
 @Inject
  public CompanyValidatorImpl(Validator validator) {
    this.validator = validator;
  }

  @Override
  public List<String> validate(Company company) {
    Set<ConstraintViolation<Company>> violations = validator.validate(company);
    List<String> errors = new ArrayList<String>();
    for (ConstraintViolation<Company> violation : violations) {
      errors.add(violation.getMessage());
    }
    return errors;
  }
}
