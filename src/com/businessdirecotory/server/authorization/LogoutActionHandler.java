package com.businessdirecotory.server.authorization;

import com.businessdirecotory.shared.entites.actions.LogoutAction;
import com.businessdirecotory.shared.entites.reponses.LogoutResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class LogoutActionHandler implements ActionHandler<LogoutAction, LogoutResponse> {


  private AuthorizedTokensRepository tokensRepository;

  @Inject
  public LogoutActionHandler(AuthorizedTokensRepository tokensRepository) {
    this.tokensRepository = tokensRepository;
  }


  @Override
  public LogoutResponse handle(LogoutAction action) {
    ArrayList<String> errors = new ArrayList<String>();
    LogoutResponse response = new LogoutResponse(errors);
    tokensRepository.delete(action.getToken());
    return response;
  }
}
