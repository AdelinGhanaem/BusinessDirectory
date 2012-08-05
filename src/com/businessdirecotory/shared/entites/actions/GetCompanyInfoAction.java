package com.businessdirecotory.shared.entites.actions;

import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class GetCompanyInfoAction implements Action{

  private Long companyId;

  public GetCompanyInfoAction(Long companyId) {

    this.companyId = companyId;
  }

  public Long getCompanyId() {
    return companyId;
  }
}
