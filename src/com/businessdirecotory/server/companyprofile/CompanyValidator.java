package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.Company;

import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyValidator {
  List<String> validate(Company company);
}
