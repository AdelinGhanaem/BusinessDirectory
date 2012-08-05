package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.CompanyInfo;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class GetCompanyInfoResponse implements Response {

  private CompanyInfo companyInfo;

  public GetCompanyInfoResponse() {
  }

  public GetCompanyInfoResponse(CompanyInfo companyInfo) {

    this.companyInfo = companyInfo;
  }

  public CompanyInfo getCompanyInfo() {
    return companyInfo;
  }
}
