package com.businessdirecotory.shared.entites.actions;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchAction<SearchResponse extends Response> implements Action<SearchResponse> {

  private String keyword;

  public SearchAction(String keyword) {

    this.keyword = keyword;
  }

  public SearchAction() {
  }


  public String getKeyWord() {
    return keyword;
  }
}
