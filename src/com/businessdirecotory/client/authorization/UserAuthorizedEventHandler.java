package com.businessdirecotory.client.authorization;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface UserAuthorizedEventHandler extends EventHandler {
  void onUserAuthorized(UserAuthorizedEvent event);
}
