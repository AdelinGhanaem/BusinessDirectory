package com.businessdirecotory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("businessdirecotoryService")
public interface BusinessdirecotoryService extends RemoteService {
  // Sample interface method of remote interface
  String getMessage(String msg);

  /**
   * Utility/Convenience class.
   * Use BusinessdirecotoryService.App.getInstance() to access static instance of businessdirecotoryServiceAsync
   */
  public static class App {
    private static BusinessdirecotoryServiceAsync ourInstance = GWT.create(BusinessdirecotoryService.class);

    public static synchronized BusinessdirecotoryServiceAsync getInstance() {
      return ourInstance;
    }
  }
}
