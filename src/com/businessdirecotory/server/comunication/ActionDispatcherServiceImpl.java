package com.businessdirecotory.server.comunication;

import com.businessdirecotory.client.authorization.SecuredAction;
import com.businessdirecotory.client.authorization.SecuredResponse;
import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.authorization.AuthorizedTokensRepository;
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

import java.io.Serializable;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
@Singleton
public class ActionDispatcherServiceImpl extends RemoteServiceServlet implements ActionDispatcherService, ActionDispatcher, Serializable {


  private ActionHandlerRepository repository;
  private AuthorizedTokensRepository authorizedTokens;

  @Inject
  public ActionDispatcherServiceImpl(ActionHandlerRepository repository, AuthorizedTokensRepository authorizedAccountsTokensRepository) {
    this.repository = repository;
    this.authorizedTokens = authorizedAccountsTokensRepository;
  }

  public ActionDispatcherServiceImpl() {

  }

  @Override
  public <T extends Response> T dispatch(Action<T> action) throws ActionHandlerNotBoundException {
    if (action instanceof SecuredAction) {
      return (T) dispatchSecuredAction((SecuredAction<Response>) action);
    }

    ActionHandler handler = repository.getActionHandler(action.getClass());

    if (handler == null) {
      throw new NoActionHandlerException();
    }


    return (T) handler.handle(action);

  }

  private <T extends Response> SecuredResponse dispatchSecuredAction(SecuredAction<T> action) {
    Token token = ((SecuredAction) action).getToken();
    if (!authorizedTokens.isAuthorized(token, new Date())) {
      return new SecuredResponse(null);
    } else {
      ActionHandler actionHandler = repository.getActionHandler(action.getAction().getClass());
      Response response = actionHandler.handle(action.getAction());
      return new SecuredResponse<T>((T) response);
    }
  }


}