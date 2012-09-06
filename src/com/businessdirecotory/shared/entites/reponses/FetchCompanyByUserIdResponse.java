package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchCompanyByUserIdResponse implements Response {


  public FetchCompanyByUserIdResponse(Company company) {
    this.company = company;
  }

  public FetchCompanyByUserIdResponse() {
  }

  private Company company;

  public Company getCompany() {
    return company;
  }
}
