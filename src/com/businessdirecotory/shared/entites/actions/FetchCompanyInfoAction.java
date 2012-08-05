package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.reponses.FetchCompanyInfoResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyInfoAction implements Action<FetchCompanyInfoResponse> {
  private Long companyId;

  public FetchCompanyInfoAction(Long companyId) {
    this.companyId = companyId;
  }

  public Long getCompanyId() {
    return companyId;
  }
}
