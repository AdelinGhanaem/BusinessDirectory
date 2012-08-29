package com.businessdirecotory.client.comunication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@RemoteServiceRelativePath("ActionDispatcherService")
public interface ActionDispatcherService extends RemoteService {

  <T extends Response> T dispatch(Action<T> action) ;

}
