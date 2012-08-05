package com.businessdirecotory.client.navigation;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import java.util.HashMap;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ApplicationActivityMapper implements ActivityMapper {

  private HashMap<Class<? extends Place>, ActivityPlaceMeta> placesActivitiesMap;

  @Inject
  public ApplicationActivityMapper(HashMap<Class<? extends Place>, ActivityPlaceMeta> placesActivitiesMap) {
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

    ActivityPlaceMeta returnedActivity = placesActivitiesMap.get(place.getClass());
    if (returnedActivity == null) {
      return placesActivitiesMap.get(PlaceNotFoundPlace.class).getActivity(new PlaceNotFoundPlace());
    }
    return returnedActivity.getActivity(place);
  }

}
