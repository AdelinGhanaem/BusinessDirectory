package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.companyregistration.view.CompanyRegistrationViewImpl;
import com.businessdirecotory.client.navigation.activites.CompanyRegistrationActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import java.util.HashMap;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ApplicationActivityMapper implements ActivityMapper {

  private HashMap<Class<? extends Place>, Activity> placesActivitiesMap;

  @Inject
  public ApplicationActivityMapper(HashMap<Class<? extends Place>, Activity> placesActivitiesMap) {
    this.placesActivitiesMap = placesActivitiesMap;
  }

  @Override
  public Activity getActivity(Place place) {
    Activity returnedActivity = placesActivitiesMap.get(place.getClass());
    return returnedActivity;
  }

}
