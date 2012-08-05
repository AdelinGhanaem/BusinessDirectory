package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.navigation.places.AuthorizationPlace;
import com.businessdirecotory.client.navigation.places.CompanyProfilePlace;
import com.businessdirecotory.client.navigation.places.FullInfoPlace;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.businessdirecotory.client.navigation.places.UserRegistrationPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@WithTokenizers({SearchPlace.Tokenizer.class,
        AuthorizationPlace.Tokenizer.class,
        UserRegistrationPlace.Tokenizer.class,
        CompanyProfilePlace.Tokenizer.class ,
        FullInfoPlace.Tokenizer.class
  })
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {
}
