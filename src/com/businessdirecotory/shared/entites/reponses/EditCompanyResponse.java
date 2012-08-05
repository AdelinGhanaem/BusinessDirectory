package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class EditCompanyResponse implements Response {


  private Company company;

  private ArrayList<String> errors;

  public EditCompanyResponse(Company company, ArrayList<String> errors) {
    this.company = company;
    this.errors = errors;
  }

  public EditCompanyResponse() {
  }

  public Company getCompany() {
    return company;
  }

  public ArrayList<String> getErrors() {
    return errors;
  }
}
