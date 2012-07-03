package com.businessdirecotory.client.companyregistration;

import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegistrationResponse implements Response {
  private ArrayList<String> errors;

  public CompanyRegistrationResponse(ArrayList<String> errors) {

    this.errors = errors;
  }

  public CompanyRegistrationResponse() {
  }

  public ArrayList<String> getErrors() {
    return errors;
  }
}
