package com.businessdirecotory.client.authorization;

import com.businessdirecotory.client.navigation.NavigationBarView;
import com.businessdirecotory.client.navigation.WidgetsContainer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserAuthorizedEventHandlerImplTest {

  @Mock
  private SecurityTokenProvider securityTokenProvider;

  @Mock
  public NavigationBarView navigationBarView;

  @Mock
  WidgetsContainer widgetsContainer;

  UserAuthorizedEventHandlerImpl userAuthorizedEventHandler;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
//    userAuthorizedEventHandler = new UserAuthorizedEventHandlerImpl(securityTokenProvider, navigationBarView, widgetsContainer);
  }

  @Test
  public void setsSecurityToken() {

//    String user = "user";
//
//    Token token = new Token(1l, 1l, user, new Date());
//
//    userAuthorizedEventHandler.onUserAuthorized(new UserAuthorizedEvent(token));
//
//    verify(securityTokenProvider).setToken(token);
  }

  @Test
  public void showAuthorizedUserMenuBar() {

//    String user = "user";
//
//    Token token = new Token(1l, 1l, user, new Date());
//
//    userAuthorizedEventHandler.onUserAuthorized(new UserAuthorizedEvent(token));
//
//    verify(navigationBarView).setUsernameBrand(user);
//
//    verify(navigationBarView).showLogoutButton();
//
//    verify(navigationBarView).hideLoginMenuItem();


  }


//  @Test
//  public void addsNewNavigationMenuToWidgetsContainer() {
//    String user = "user";
//
//    Token token = new Token(user);
//
//
//    userAuthorizedEventHandler.onUserAuthorized(new UserAuthorizedEvent(token));
//
////    verify(widgetsContainer).addToMenu();
//  }

}
