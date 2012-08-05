package com.businessdirecotory.client.companyInformation;

import com.businessdirecotory.shared.entites.CompanyInfo;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompanyInfoView {

  void showCompanyInfo(CompanyInfo companyInfo);

  void notifyUserNoCompanyInfo();

}
