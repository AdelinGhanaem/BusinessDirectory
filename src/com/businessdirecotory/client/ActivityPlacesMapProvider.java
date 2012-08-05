package com.businessdirecotory.client;

import com.businessdirecotory.client.authorization.AuthorizationPresenter;
import com.businessdirecotory.client.companyprofile.CompanyProfilePresenter;
import com.businessdirecotory.client.navigation.ActivityPlaceMeta;
import com.businessdirecotory.client.navigation.places.AuthorizationPlace;
import com.businessdirecotory.client.navigation.places.CompanyProfilePlace;
import com.businessdirecotory.client.navigation.places.UserRegistrationPlace;
import com.businessdirecotory.client.navigation.places.FullInfoPlace;
import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.businessdirecotory.client.registration.UserRegistrationPresenterImpl;
import com.businessdirecotory.client.search.CompanyFullInfoPresenter;
import com.businessdirecotory.client.search.SearchPresenter;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.HashMap;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ActivityPlacesMapProvider implements Provider<HashMap<Class<? extends Place>, ActivityPlaceMeta>> {
  @Inject
  UserRegistrationPresenterImpl companyRegistrationActivity;

  @Inject
  SearchPresenter searchPresenter;

  @Inject
  AuthorizationPresenter authorizationPresenter;

  @Inject
  CompanyProfilePresenter companyProfilePresenter;

  @Inject
  CompanyFullInfoPresenter companyFullInfoPresenter;

  @Override
  public HashMap<Class<? extends Place>, ActivityPlaceMeta> get() {

    HashMap<Class<? extends Place>, ActivityPlaceMeta> hashMap = new HashMap<Class<? extends Place>, ActivityPlaceMeta>();
    hashMap.put(UserRegistrationPlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {return companyRegistrationActivity;}});
    hashMap.put(SearchPlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {
        return searchPresenter;
      }
    });
    hashMap.put(AuthorizationPlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {
        return authorizationPresenter;
      }
    });
    hashMap.put(CompanyProfilePlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {
        return companyProfilePresenter;
      }
    });
    hashMap.put(FullInfoPlace.class, new ActivityPlaceMeta<FullInfoPlace, CompanyFullInfoPresenter>() {

      @Override
      public CompanyFullInfoPresenter getActivity(FullInfoPlace fullInfoPlace) {
        companyFullInfoPresenter.fetchFullInfo(fullInfoPlace.getId());
        return companyFullInfoPresenter;
      }
    });
    return hashMap;
  }
}
