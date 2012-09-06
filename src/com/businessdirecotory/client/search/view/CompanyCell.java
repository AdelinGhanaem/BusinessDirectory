package com.businessdirecotory.client.search.view;

import com.businessdirecotory.client.resources.ImagesResource;
import com.businessdirecotory.shared.entites.Company;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

import java.util.Map;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyCell extends AbstractCell<Company> {

  private Map<String, String> iconsMap;

  private ImagesResource instance;

  public CompanyCell(ImagesResource instance) {

    this.instance = instance;
  }


  @Override
  public void render(Context context, Company value, SafeHtmlBuilder sb) {

    if (value == null) {
      return;
    }
    String email = AbstractImagePrototype.create(instance.email()).getHTML();
    String activity = AbstractImagePrototype.create(instance.activity()).getHTML();
    String contactFace = AbstractImagePrototype.create(instance.contactFace()).getHTML();
    String address = AbstractImagePrototype.create(instance.map()).getHTML();
    String phoneNumber = AbstractImagePrototype.create(instance.phoneNumber()).getHTML();
//    String facebook = AbstractImagePrototype.create(instance.faceBook()).getHTML();
//    String googlePlus = AbstractImagePrototype.create(instance.plus()).getHTML();
//    String twitter = AbstractImagePrototype.create(instance.twitter()).getHTML();

    sb.appendHtmlConstant(" <div class=\"companyDivCell\">\n" +
            "                <table class=\"companyTable\">\n" +
//            "                    <tr><td rowspan=\"20\"><img src=\"" + value.getLogoURL() + "\"/></td></tr>\n" +
            "                    <tr><td colspan=\"2\" class=\"nameCell\">" + value.getInfo().getCompanyName() + "</td></tr>\n" +
            "                    <tr><td colspan=\"2\">" + value.getInfo().getActivity() + "</td></tr>\n" +
            "                    <tr><td>" + phoneNumber + " Телефон</td><td>" + value.getInfo().getPhoneNumber() + "</td></tr>\n" +
            "                    <tr><td>" + address + " Град</td><td>" + value.getAddress().getCity()             + "</td></tr>\n" +
            "                    <tr><td>" + email + " e-mail</td><td>" + value.getInfo().getEmail() + "</td></tr>\n" +
//            "                    <tr><td>" + address + " Адрес:</td><td>" + value.getAddress() + "</td></tr>\n" +
            "                    <tr><td>" + contactFace + "лице за контакт:</td><td>" + value.getInfo().getContactFace() + "</td></tr>\n" +
            "                    <tr><td>" + activity + " Дейност :</td><td>" + value.getInfo().getActivity() + "</td></tr>\n" +
//            "                    <tr><td><a href=\""+value.getFacebook()+"\" target =\"_blank\"   >"+facebook+"</a></td>" +
//                                    "<td><a href=\""+value.getGooglePlus()+"\" target =\"_blank\"   >"+googlePlus+"</a></td>\n" +
//                                    "<td><a href=\""+value.getTwitter()+"\" target =\"_blank\"   >"+twitter+"</a></td>\n" +

            "                    <tr><td></td></tr>\n" +
            "                </table>\n" +
            "            </div>");

  }

}
