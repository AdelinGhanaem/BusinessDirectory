package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.CompanyImage;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyByIdResponse implements Response {


  private Company companyProfile;

  private CompanyImage image;

  public FetchCompanyByIdResponse(Company companyProfile, CompanyImage image) {

    this.companyProfile = companyProfile;
    this.image = image;
  }

  public FetchCompanyByIdResponse(Company companyProfile) {

    this.companyProfile = companyProfile;
    this.image = image;
  }

  public FetchCompanyByIdResponse() {
  }

  public Company getCompany() {
    return companyProfile;
  }

  public CompanyImage getImage() {
    return image;
  }
}
