package com.businessdirecotory.client.companyprofile.view;

import com.businessdirecotory.client.companyprofile.CompanyProfilePresenter;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.CompanyInfo;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyProfileView extends IsWidget {

  void showCompanyProfile(Company companyProfile);

  void setPresenter(CompanyProfilePresenter presenter);

  void initializeUploadForm(String url);

  void disableEditButton();

  void enableEditButton();

  void showCreateProfileButton();

  void updateImageURL(String imageURL);

  void showCompanyInfo(CompanyInfo info);

}
