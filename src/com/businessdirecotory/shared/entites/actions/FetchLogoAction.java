package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.reponses.FetchLogoResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FetchLogoAction implements Action<FetchLogoResponse> {
  private Long userId;

  public FetchLogoAction(Long userId) {

    this.userId = userId;
  }

  public FetchLogoAction() {
  }

  public Long getUserId() {
    return userId;
  }
}
