package com.businessdirecotory.server.authorization;

import com.businessdirecotory.client.authorization.Token;
import com.businessdirecotory.server.registration.UserRepository;
import com.businessdirecotory.server.registration.UserValidator;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.Calendar;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationActionHandler implements ActionHandler<AuthorizationAction, AuthorizationResponse> {


  private UserRepository repository;
  private AuthorizedTokensRepository authorizedTokensRepository;
  private IdGenerator idGenerator;
  private UserValidator validator;

  @Inject
  public AuthorizationActionHandler(UserRepository userRepository,
                                    AuthorizedTokensRepository authorizedTokensRepository,
                                    IdGenerator idGenerator, UserValidator validator) {
    repository = userRepository;
    this.authorizedTokensRepository = authorizedTokensRepository;
    this.idGenerator = idGenerator;
    this.validator = validator;
  }

  @Override
  public AuthorizationResponse handle(AuthorizationAction action) {

    Token token = null;

    List<String> errors = validator.validate(action.getUser());
    if (errors.size() > 0) {
      return new AuthorizationResponse(token);
    }
    Long userId = repository.getUserId(action.getUser().getUsername(), action.getUser().getPassword());
    if (userId != null) {
      Long tokeId = idGenerator.generateId();
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.YEAR, 2);
      token = new Token(tokeId, userId, action.getUser().getUsername(), calendar.getTime());
      authorizedTokensRepository.add(token, calendar.getTime());
    }
    return new AuthorizationResponse(token);
  }
}
