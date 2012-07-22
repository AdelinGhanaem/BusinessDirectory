package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyResponse implements Response {


  private Company company;

  public EditCompanyResponse(Company company) {
    this.company = company;
  }

  public EditCompanyResponse() {
  }

  public Company getCompany() {
    return company;
  }
}
