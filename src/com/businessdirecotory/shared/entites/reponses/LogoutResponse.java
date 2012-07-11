package com.businessdirecotory.shared.entites.reponses;

import com.evo.gad.shared.Response;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class LogoutResponse implements Response {

  private ArrayList<String> errors;

  public LogoutResponse() {
  }

  public LogoutResponse(ArrayList<String> errors) {

    this.errors = errors;
  }

  public ArrayList<String> getErrors() {
    return errors;
  }
}
