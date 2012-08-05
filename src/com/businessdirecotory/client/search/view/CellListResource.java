package com.businessdirecotory.client.search.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CellListResource extends CellList.Resources {


  public static CellListResource INSTANCE = GWT.create(CellListResource.class);

  interface CellListStyle extends CellList.Style {

  }

  @Source({CellList.Style.DEFAULT_CSS,"MyCellTableStyle.css"})
  CellListStyle style();
}
