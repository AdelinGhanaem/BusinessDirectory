package com.businessdirecotory.client.search;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class ViewFullInfoEvent extends GwtEvent<ViewFullInfoEventHandler> {
  public static Type<ViewFullInfoEventHandler> TYPE = new Type<ViewFullInfoEventHandler>();

  private long companyId;

  public ViewFullInfoEvent(long companyId) {

    this.companyId = companyId;
  }

  public Type<ViewFullInfoEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ViewFullInfoEventHandler handler) {
    handler.onViewFullInfo(this);
  }

  public long getCompanyId() {
    return companyId;
  }
}
