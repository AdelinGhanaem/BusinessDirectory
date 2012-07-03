package com.businessdirecotory.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BusinessdirecotoryServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
