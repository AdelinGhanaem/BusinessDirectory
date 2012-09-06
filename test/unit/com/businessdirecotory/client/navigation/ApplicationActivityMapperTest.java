package com.businessdirecotory.client.navigation;

import com.businessdirecotory.client.authorization.SecurityTokenProvider;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ApplicationActivityMapperTest {


  @Mock
  ActionDispatcherServiceAsync service;


  private class TestActivity extends AbstractActivity {
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {

    }
  }

  TestActivity testActivity = new TestActivity();

  private class TestPlace extends Place {
  }

  private ApplicationActivityMapper mapper;

  @Mock
  private SecurityTokenProvider provider;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    HashMap<Class<? extends Place>, ActivityPlaceMeta> placesActivitiesMap = new HashMap<Class<? extends Place>, ActivityPlaceMeta>();

    placesActivitiesMap.put(TestPlace.class, new ActivityPlaceMeta() {
      @Override
      public Activity getActivity(Place place) {
        return new TestActivity();
      }
    });

    placesActivitiesMap.put(MySecuredPlace.class, new ActivityPlaceMeta<MySecuredPlace, MySecuredActivity>() {
      @Override
      public MySecuredActivity getActivity(MySecuredPlace mySecuredPlace) {
        return new MySecuredActivity();
      }
    });


    mapper = new ApplicationActivityMapper(service,provider, placesActivitiesMap);


  }


  @Test
  public void shouldReturnActivity() {
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


//  @Test
//  public void returnsSearchActivityIsUserIsNotAuthorized() {
//    when(provider.getToken()).thenReturn(new Token());
//    CheckAuthorizationResponse response = new CheckAuthorizationResponse(new Token());
//    doOnSuccess(response).when(service).dispatch(isA(CheckAuthorizationAction.class), isA(GotResponse.class));
//    Activity activity = mapper.getActivity(new MySecuredPlace());
//    verify(provider).getToken();
//    verify(service).dispatch(isA(CheckAuthorizationAction.class), isA(GotResponse.class));
//    assertThat(activity, is(notNullValue()));
//    assertEquals(MySecuredActivity.class, activity.getClass());
//
//  }
//

  class MySecuredPlace extends SecuredPlace {

  }

  class MySecuredActivity extends AbstractActivity {

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

}
