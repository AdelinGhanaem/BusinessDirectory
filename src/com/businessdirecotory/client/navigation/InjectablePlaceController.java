package com.businessdirecotory.client.navigation;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.mockito.InjectMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class InjectablePlaceController extends PlaceController {
  @Inject
  public InjectablePlaceController(EventBus eventBus) {
    super(eventBus);
  }
}
