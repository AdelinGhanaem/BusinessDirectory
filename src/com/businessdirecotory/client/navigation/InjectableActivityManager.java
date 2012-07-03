package com.businessdirecotory.client.navigation;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;


/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class InjectableActivityManager extends ActivityManager {
  /**
   * Create an ActivityManager. Next call {@link #setDisplay}.
   *
   * @param mapper   finds the {@link com.google.gwt.activity.shared.Activity} for a given
   *                 {@link com.google.gwt.place.shared.Place}
   * @param eventBus source of {@link com.google.gwt.place.shared.PlaceChangeEvent} and
   *                 {@link com.google.gwt.place.shared.PlaceChangeRequestEvent} events.
   */
  @Inject
  public InjectableActivityManager(ActivityMapper mapper, EventBus eventBus) {
    super(mapper, eventBus);
  }
}
