package com.businessdirecotory.client.comunication;

import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public abstract class GotResponse<T extends Response> implements AsyncCallback<T> {
  @Override
  public void onFailure(Throwable caught) {

  }

  @Override
  public void onSuccess(T result) {
    gotResponse(result);
  }

  public abstract void gotResponse(T result);
}
