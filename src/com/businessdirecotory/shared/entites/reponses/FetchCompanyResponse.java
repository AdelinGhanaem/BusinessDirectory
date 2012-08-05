package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.CompanyImage;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyResponse implements Response {


  private Company companyProfile;

  private CompanyImage image;

  public FetchCompanyResponse(Company companyProfile, CompanyImage image) {

    this.companyProfile = companyProfile;
    this.image = image;
  }

  public FetchCompanyResponse(Company companyProfile) {

    this.companyProfile = companyProfile;
    this.image = image;
  }

  public FetchCompanyResponse() {
  }

  public Company getCompany() {
    return companyProfile;
  }

  public CompanyImage getImage() {
    return image;
  }
}
