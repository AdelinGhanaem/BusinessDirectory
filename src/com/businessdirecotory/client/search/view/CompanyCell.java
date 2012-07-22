package com.businessdirecotory.client.search.view;

import com.businessdirecotory.shared.entites.Company;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import java.util.Map;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyCell extends AbstractCell<Company> {

  private Map<String, String> iconsMap;

  public CompanyCell() {

  }


  @Override
  public void render(Context context, Company value, SafeHtmlBuilder sb) {

    if (value == null) {
      return;
    }
    sb.appendHtmlConstant(" <div class=\"companyDivCell\">\n" +
            "                <table class=\"companyTable\">\n" +
            "                    <tr><td rowspan=\"9\">Logo</td></tr>\n" +
            "                    <tr><td colspan=\"2\" class=\"nameCell\">" + value.getName() + "</td></tr>\n" +
            "                    <tr><td colspan=\"2\">" + value.getDescription() + "</td></tr>\n" +
            "                    <tr><td>Дейност:</td><td>" + value.getActivity() + "</td></tr>\n" +
            "                    <tr><td>Град:</td><td>" + value.getLocation() + "</td></tr>\n" +
            "                    <tr><td>Адрес:</td><td>" + value.getAddress() + "</td></tr>\n" +
            "                    <tr><td>e-mail</td><td>" + value.getEmail() + "</td></tr>\n" +
            "                    <tr><td>лице за контакт:</td><td>" + value.getContactFace() + "</td></tr>\n" +
            "                    <tr><td></td></tr>\n" +
            "                </table>\n" +
            "            </div>");
  }
}
