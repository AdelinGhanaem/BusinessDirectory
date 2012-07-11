package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationResponse;
import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationAction implements Action<CompanyRegistrationResponse> {


  private Company account;

  public CompanyRegistrationAction(Company company) {
    this.account = company;
  }

  public CompanyRegistrationAction() {

  }

  public Company getCompany() {
    return account;
  }
}


