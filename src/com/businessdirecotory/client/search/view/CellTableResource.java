package com.businessdirecotory.client.search.view;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.core.client.GWT;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CellTableResource extends CellTable.Resources {


  public static CellTableResource INSTANCE = GWT.create(CellTableResource.class);



  public interface CellTableStyle extends CellTable.Style {
  }

  @Source({"CellTable.css"})
  CellTableStyle style();
}
