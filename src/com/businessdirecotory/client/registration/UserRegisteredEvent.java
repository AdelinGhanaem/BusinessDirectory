package com.businessdirecotory.client.registration;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class UserRegisteredEvent extends GwtEvent<UserRegisteredEventHandler> {
  public static Type<UserRegisteredEventHandler> TYPE = new Type<UserRegisteredEventHandler>();

  public Type<UserRegisteredEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UserRegisteredEventHandler handler) {
    handler.onAccountRegistered(this);
  }
}
