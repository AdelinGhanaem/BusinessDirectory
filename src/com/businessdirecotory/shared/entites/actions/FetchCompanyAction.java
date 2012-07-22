package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.reponses.FetchCompanyResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyAction implements Action<FetchCompanyResponse> {

  private String companyEmail;

  public FetchCompanyAction(String companyEmail) {
    this.companyEmail = companyEmail;
  }

  public FetchCompanyAction() {
  }

  public String getCompanyEmail() {
    return companyEmail;
  }
}
