package com.businessdirecotory.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<AuthorizationPlace> {
    @Override
    public String getToken(AuthorizationPlace place) {
      return "authorization";
    }

    @Override
    public AuthorizationPlace getPlace(String token) {
      return new AuthorizationPlace();
    }
  }
}
