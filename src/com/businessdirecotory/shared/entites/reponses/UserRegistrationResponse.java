package com.businessdirecotory.shared.entites.reponses;

import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationResponse implements Response {

  private ArrayList<String> errors;

  public UserRegistrationResponse(ArrayList<String> errors) {

    this.errors = errors;
  }

  public UserRegistrationResponse() {
  }

  public ArrayList<String> getErrors() {
    return errors;
  }
}
