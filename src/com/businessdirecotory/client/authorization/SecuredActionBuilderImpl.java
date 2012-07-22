package com.businessdirecotory.client.authorization;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SecuredActionBuilderImpl implements SecuredActionBuilder {

  private SecurityTokenProvider provider;

  @Inject

  public SecuredActionBuilderImpl(SecurityTokenProvider provider) {

    this.provider = provider;
  }

  public <R extends Response, A extends Action<R>> SecuredAction build(A action) {
    return new SecuredAction(action, provider.getToken());
  }
}
