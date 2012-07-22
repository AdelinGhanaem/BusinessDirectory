package com.businessdirecotory.client.authorization;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface SecuredActionBuilder  {

  public <R extends Response, A extends Action<R>> SecuredAction<SecuredResponse<R>> build(A action);
}
