package com.businessdirecotory.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegistrationPlace extends Place {


  public static class Tokenizer implements PlaceTokenizer<UserRegistrationPlace> {
    @Override
    public String getToken(UserRegistrationPlace place) {
      return "registration";
    }

    @Override
    public UserRegistrationPlace getPlace(String token) {
      return new UserRegistrationPlace();
    }
  }
}
