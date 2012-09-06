package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.reponses.FetchCompanyByUserIdResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchCompanyByUserIdAction implements Action<FetchCompanyByUserIdResponse> {
  private  long userId;

  public FetchCompanyByUserIdAction(long userId) {
    this.userId = userId;
  }

  public FetchCompanyByUserIdAction() {
  }

  public long getUserId() {
    return userId;
  }

}
