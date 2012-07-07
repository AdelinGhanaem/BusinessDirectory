package com.businessdirecotory.client.comunication;

import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.ActionHandlerNotBoundException;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@RemoteServiceRelativePath("ActionDispatcherService")
public interface ActionDispatcherService extends RemoteService {

  <T extends Response> T dispatch(Action<T> action) ;


}
