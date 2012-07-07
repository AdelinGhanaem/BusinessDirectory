package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.shared.entites.Company;

import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyValidator {
  public List<String> validate(Company company);
}