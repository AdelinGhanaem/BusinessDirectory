package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.shared.entites.actions.CheckAuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.CheckAuthorizationResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CheckAuthorizationResponseActionHandler implements
        ActionHandler<CheckAuthorizationAction, CheckAuthorizationResponse> {

  private AuthorizedTokensRepository repository;
  private IdGenerator idGenerator;

  @Inject
  public CheckAuthorizationResponseActionHandler(AuthorizedTokensRepository repository, IdGenerator idGenerator) {

    this.repository = repository;
    this.idGenerator = idGenerator;
  }

  @Override
  public CheckAuthorizationResponse handle(CheckAuthorizationAction action) {

    if (repository.isAuthorized(action.getToken(), new Date())) {
      Long id = idGenerator.generateId();
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.YEAR, 2);
      Token newToken = new Token(id, action.getToken().getUserId(), action.getToken().getUser(), calendar.getTime());
      return null;
    }
    return null;
  }
}
