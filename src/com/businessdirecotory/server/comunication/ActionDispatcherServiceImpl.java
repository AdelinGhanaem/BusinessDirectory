package com.businessdirecotory.server.comunication;

import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.ActionHandlerNotBoundException;
import com.evo.gad.shared.Response;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.businessdirecotory.client.comunication.ActionDispatcherService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@Singleton
public class ActionDispatcherServiceImpl extends RemoteServiceServlet implements ActionDispatcherService, ActionDispatcher, Serializable {


  private ActionHandlerRepository repository;

  @Inject
  public ActionDispatcherServiceImpl(ActionHandlerRepository repository) {

    this.repository = repository;
  }

  @Override
  public <T extends Response> T dispatch(Action<T> action) throws ActionHandlerNotBoundException {
    ActionHandler handler = repository.getActionHandler(action.getClass());
    Response response = null;
    if (handler != null) {
      response = handler.handle(action);
    }
    return (T) response;
  }


}