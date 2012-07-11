package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationActionHandler implements ActionHandler<AuthorizationAction, AuthorizationResponse> {


  private CompaniesRepository repository;
  private AuthorizedAccountsTokensRepository authorizedAccountTokensRepository;
  private SessionExpireTimeProvider expireTimeProvider;

  @Inject
  public AuthorizationActionHandler(CompaniesRepository repository,
                                    AuthorizedAccountsTokensRepository authorizedAccountTokensRepository,
                                    SessionExpireTimeProvider expireTimeProvider) {
    this.repository = repository;
    this.authorizedAccountTokensRepository = authorizedAccountTokensRepository;
    this.expireTimeProvider = expireTimeProvider;
  }

  @Override
  public AuthorizationResponse handle(AuthorizationAction action) {
    Token token = null;
    if (repository.isRegistered(action.getAccount())) {
      token = new Token(action.getAccount().getUsername(), expireTimeProvider.getSessionExpireTime());
      authorizedAccountTokensRepository.add(token);
    }
    return new AuthorizationResponse(token);
  }
}
