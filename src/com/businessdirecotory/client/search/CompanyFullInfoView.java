package com.businessdirecotory.client.search;

import com.businessdirecotory.shared.entites.Company;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyFullInfoView {

  void viewCompany(Company company);

  void notifyUserWhenCompanyIsNull();
}
