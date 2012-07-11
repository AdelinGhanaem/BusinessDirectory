package com.businessdirecotory.client.authorization;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserAuthorizedEvent extends GwtEvent<UserAuthorizedEventHandler> {

  public static Type<UserAuthorizedEventHandler> TYPE = new Type<UserAuthorizedEventHandler>();
  private Token token;

  public UserAuthorizedEvent(Token token) {

    this.token = token;
  }

  public Token getToken() {
    return token;
  }

  public Type<UserAuthorizedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UserAuthorizedEventHandler handler) {
    handler.onUserAuthorized(this);
  }
}
