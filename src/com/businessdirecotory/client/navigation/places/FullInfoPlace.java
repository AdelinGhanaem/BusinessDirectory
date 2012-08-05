package com.businessdirecotory.client.navigation.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class FullInfoPlace extends Place {


  public FullInfoPlace(long id) {
    this.id = id;
  }

  private long id;

  public long getId() {
    return id;
  }

  public static class Tokenizer implements PlaceTokenizer<FullInfoPlace> {
    @Override
    public String getToken(FullInfoPlace place) {
      return String.valueOf(place.getId());
    }

    @Override
    public FullInfoPlace getPlace(String token) {
      return new FullInfoPlace(Long.valueOf(token));
    }
  }
}
