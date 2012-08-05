package com.businessdirecotory.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchPlace extends Place {


  public static class Tokenizer implements PlaceTokenizer<SearchPlace> {
    @Override
    public String getToken(SearchPlace place) {
      return "search";
    }

    @Override
    public SearchPlace getPlace(String token) {
      return new SearchPlace();
    }
  }
}
