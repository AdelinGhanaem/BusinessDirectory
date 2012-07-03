package com.businessdirecotory.shared.entites.reponses;

import com.businessdirecotory.shared.entites.Company;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchResponse implements Response,IsSerializable {

  private ArrayList<Company> companies;

  public SearchResponse(ArrayList<Company> companies) {

    this.companies = companies;
  }

  public SearchResponse() {
  }

  public ArrayList<Company> getCompanies() {
    return companies;
  }
}
