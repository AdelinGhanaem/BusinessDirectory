package com.businessdirecotory.client.authorization.view;

import com.businessdirecotory.client.authorization.AuthorizationPresenter;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface AuthorizationView {

  void hide();

  void notifyUserOfWrongUsernameOrPassword();

  void setPresenter(AuthorizationPresenter authorizationPresenter);

  void show();
}
