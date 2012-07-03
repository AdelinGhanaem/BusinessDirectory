package com.businessdirecotory.client.companyregistration;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyRegisteredEvent extends GwtEvent<CompanyRegisteredEventHandler> {
  public static Type<CompanyRegisteredEventHandler> TYPE = new Type<CompanyRegisteredEventHandler>();

  public Type<CompanyRegisteredEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(CompanyRegisteredEventHandler handler) {
    handler.onAccountRegistered(this);
  }
}
