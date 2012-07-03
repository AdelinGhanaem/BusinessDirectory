package com.businessdirecotory.client;

import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenter;
import com.businessdirecotory.client.companyregistration.CompanyRegistrationPresenterImpl;
import com.businessdirecotory.client.navigation.activites.CompanyRegistrationActivity;
import com.businessdirecotory.client.navigation.places.CompanyRegistrationPlace;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.businessdirecotory.client.search.SearchPresenter;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.ProvidedBy;
import com.google.inject.Provider;

import java.util.HashMap;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ActivityPlacesMapProvider implements Provider<HashMap<Class<? extends Place>, Activity>> {
  @Inject
  CompanyRegistrationPresenterImpl companyRegistrationActivity;
  @Inject
  SearchPresenter searchPresenter;

  @Override
  public HashMap<Class<? extends Place>, Activity> get() {
    HashMap<Class<? extends Place>, Activity> hashMap = new HashMap<Class<? extends Place>, Activity>();
    hashMap.put(CompanyRegistrationPlace.class, companyRegistrationActivity);
    hashMap.put(SearchPlace.class, searchPresenter);
    return hashMap;
  }
}
