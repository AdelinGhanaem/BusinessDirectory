package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.reponses.EditCompanyResponse;
import com.evo.gad.shared.Action;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyAction implements Action<EditCompanyResponse> {


  private Company company;

  public EditCompanyAction(Company company) {

    this.company = company;
  }

  public EditCompanyAction() {
  }

  public Company getCompany() {
    return company;
  }


}
