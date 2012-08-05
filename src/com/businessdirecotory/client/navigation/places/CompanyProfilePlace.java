package com.businessdirecotory.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyProfilePlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<CompanyProfilePlace> {
    @Override
    public String getToken(CompanyProfilePlace place) {
      return "profile";
    }

    @Override
    public CompanyProfilePlace getPlace(String token) {
      return new CompanyProfilePlace();
    }
  }
}
