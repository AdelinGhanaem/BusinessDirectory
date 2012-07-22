package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationActionHandler implements ActionHandler<AuthorizationAction, AuthorizationResponse> {


  private CompaniesRepository repository;
  private AuthorizedTokensRepository authorizedAccountTokensRepository;
  private SessionExpireTimeProvider expireTimeProvider;

  @Inject
  public AuthorizationActionHandler(CompaniesRepository repository,
                                    AuthorizedTokensRepository authorizedAccountTokensRepository,
                                    SessionExpireTimeProvider expireTimeProvider) {
    this.repository = repository;
    this.authorizedAccountTokensRepository = authorizedAccountTokensRepository;
    this.expireTimeProvider = expireTimeProvider;
  }

  @Override
  public AuthorizationResponse handle(AuthorizationAction action) {
    Token token = null;
    Date expiredTime = expireTimeProvider.getSessionExpireTime();
    if (repository.isRegistered(action.getAccount())) {
      token = new Token(action.getAccount().getUsername(), expiredTime);
      authorizedAccountTokensRepository.add(token, expiredTime);
    }
    return new AuthorizationResponse(token);
  }
}
