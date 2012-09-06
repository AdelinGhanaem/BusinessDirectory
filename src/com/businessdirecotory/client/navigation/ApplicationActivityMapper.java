package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import java.util.HashMap;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ApplicationActivityMapper implements ActivityMapper {

  private ActionDispatcherServiceAsync service;

  private SecurityTokenProvider provider;

  private HashMap<Class<? extends Place>, ActivityPlaceMeta> placesActivitiesMap;

  @Inject
  public ApplicationActivityMapper(ActionDispatcherServiceAsync service, SecurityTokenProvider provider, HashMap<Class<? extends Place>, ActivityPlaceMeta> placesActivitiesMap) {

    this.service = service;

    this.provider = provider;

    this.placesActivitiesMap = placesActivitiesMap;

    this.placesActivitiesMap.put(PlaceNotFoundPlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {
        return new PlaceNotFoundActivity();
      }
    });
  }

  @Override
  public Activity getActivity(Place place) {

    if (place instanceof SecuredPlace) {
      Token token = provider.getToken();
      if (token == null) {
        return placesActivitiesMap.get(SearchPlace.class).getActivity(new SearchPlace());
      }
    }

    ActivityPlaceMeta returnedActivity = placesActivitiesMap.get(place.getClass());

    if (returnedActivity == null) {
      return placesActivitiesMap.get(PlaceNotFoundPlace.class).getActivity(new PlaceNotFoundPlace());
    }
    return returnedActivity.getActivity(place);
  }
}
