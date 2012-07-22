package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyResponse implements Response {


  private Company companyProfile;

  public FetchCompanyResponse(Company companyProfile) {

    this.companyProfile = companyProfile;
  }

  public FetchCompanyResponse() {
  }

  public Company getCompany() {
    return companyProfile;
  }
}
