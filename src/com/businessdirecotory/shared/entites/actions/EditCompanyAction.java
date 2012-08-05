package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyAction implements Action<EditCompanyResponse> {


  private Long userId;

  private Company company;

  public EditCompanyAction(Long userId, Company company) {
    this.userId = userId;

    this.company = company;
  }



  public EditCompanyAction() {
  }

  public Long getUserId() {
    return userId;
  }

  public Company getCompany() {
    return company;
  }


}
