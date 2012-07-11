package com.businessdirecotory.client.authorization;

import com.businessdirecotory.client.authorization.view.AuthorizationView;
import com.businessdirecotory.client.comunication.ActionDispatcherServiceAsync;
import com.businessdirecotory.client.comunication.GotResponse;
import com.businessdirecotory.shared.entites.actions.AuthorizationAction;
import com.businessdirecotory.shared.entites.actions.LogoutAction;
import com.businessdirecotory.shared.entites.reponses.AuthorizationResponse;
import com.businessdirecotory.shared.entites.reponses.LogoutResponse;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class AuthorizationPresenter extends AbstractActivity {

  private ActionDispatcherServiceAsync service;
  private com.google.web.bindery.event.shared.EventBus eventBus;
  private AuthorizationView view;

  @Inject
  public AuthorizationPresenter(ActionDispatcherServiceAsync service, com.google.web.bindery.event.shared.EventBus eventBus, AuthorizationView view) {

    this.service = service;
    this.eventBus = eventBus;
    this.view = view;
  }

  public void authorize(Account account) {
    service.dispatch(new AuthorizationAction(account), new GotResponse<AuthorizationResponse>() {
      @Override
      public void gotResponse(AuthorizationResponse result) {
        if (result.getToken() != null) {
          eventBus.fireEvent(new UserAuthorizedEvent(result.getToken()));
          view.hide();
        } else {
          view.notifyUserOfWrongUsernameOrPassword();
        }
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    view.setPresenter(this);
    view.show();
  }



}
