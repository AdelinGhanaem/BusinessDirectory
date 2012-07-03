package com.businessdirecotory.client.companyregistration;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyRegisteredEventHandler extends EventHandler {
  void onAccountRegistered(CompanyRegisteredEvent event);
}
