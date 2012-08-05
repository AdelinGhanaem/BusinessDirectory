package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.navigation.places.SearchPlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class InjectablePlaceController extends PlaceController {

  private Place previousPlace;
  private Place currentPlace;

  @Inject
  public InjectablePlaceController(EventBus eventBus) {
    super(eventBus);
  }


  @Override
  public void goTo(Place newPlace) {
    previousPlace = currentPlace;
    currentPlace = newPlace;

    super.goTo(newPlace);
  }

  public void goToPreviousPlace() {
    if (previousPlace != null) {
      super.goTo(previousPlace);
    } else {
      super.goTo(new SearchPlace());
    }
  }
}
