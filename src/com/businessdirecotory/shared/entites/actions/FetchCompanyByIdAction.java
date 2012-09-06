package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.reponses.FetchCompanyByIdResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchCompanyByIdAction implements Action<FetchCompanyByIdResponse> {

  private String companyEmail;

  private Long ownerId;

  public FetchCompanyByIdAction(Long ownerId) {
    this.ownerId = ownerId;
  }

  public Long getId() {
    return ownerId;
  }

  public FetchCompanyByIdAction() {
  }



  public String getCompanyEmail() {
    return companyEmail;
  }
}
