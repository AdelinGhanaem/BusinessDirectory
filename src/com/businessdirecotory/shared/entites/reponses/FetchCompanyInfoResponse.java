package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.CompanyInfo;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyInfoResponse implements Response {

  private CompanyInfo companyInfo;

  public FetchCompanyInfoResponse() {

  }

  public FetchCompanyInfoResponse(CompanyInfo companyInfo) {
    this.companyInfo = companyInfo;
  }

  public CompanyInfo getCompanyInfo() {
    return companyInfo;
  }
}
