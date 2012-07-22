package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.reponses.UserRegistrationResponse;
import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationAction implements Action<UserRegistrationResponse> {


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


