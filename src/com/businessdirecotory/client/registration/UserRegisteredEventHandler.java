package com.businessdirecotory.client.registration;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface UserRegisteredEventHandler extends EventHandler {
  void onAccountRegistered(UserRegisteredEvent event);
}
