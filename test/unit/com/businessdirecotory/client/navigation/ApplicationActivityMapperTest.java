package com.businessdirecotory.client.navigation;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ApplicationActivityMapperTest {


  private class TestActivity extends AbstractActivity {
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {

    }
  }

  TestActivity testActivity = new TestActivity();

  private class TestPlace extends Place {
  }

  private ApplicationActivityMapper mapper;

  @Before
  public void setUp() throws Exception {
    HashMap<Class<? extends Place>, ActivityPlaceMeta> placesActivitiesMap = new HashMap<Class<? extends Place>, ActivityPlaceMeta>();

    placesActivitiesMap.put(TestPlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {
        return new TestActivity();
      }
    });

    mapper = new ApplicationActivityMapper(placesActivitiesMap);
  }


  @Test
  public void returnActivity() {
    Activity testActivity = mapper.getActivity(new TestPlace());
    assertThat(testActivity, is(notNullValue()));
    assertEquals(testActivity.getClass(), TestActivity.class);
  }


  @Test
  public void returnsPageNotFoundActivityIfPlaceIsNotFound() {

    Activity placeNotFoundActivity = mapper.getActivity(new Place() {
    });
    assertEquals(placeNotFoundActivity.getClass(), PlaceNotFoundActivity.class);
  }


}
