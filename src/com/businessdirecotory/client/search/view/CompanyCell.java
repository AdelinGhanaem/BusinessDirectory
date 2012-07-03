package com.businessdirecotory.client.search.view;

import com.businessdirecotory.shared.entites.Company;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyCell extends AbstractCell<Company>  {

  @Override
  public void render(Context context, Company value, SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<div>Company</div>");
  }
}
