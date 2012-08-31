package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.Address;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.CompanyInformation;
import com.businessdirecotory.shared.entites.LatLong;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyBuilder {

  private Address address;
  private CompanyInformation companyInformation;
  private String name = "";
  private String imageURL = "";
  private Long id = 0l;
  private Long userId = 0l;

  public void setCompanyInformation(CompanyInformation companyInformation) {
    this.companyInformation = companyInformation;
  }

  public CompanyInformation getCompanyInformation() {
    return companyInformation;
  }

  public CompanyBuilder() {
    setToDefault();
  }

  private void setToDefault() {
    address = new Address(new LatLong(0d, 0d), "County", "City", "Street", "Description");
    companyInformation = new CompanyInformation("CompanyName",
            "Contact Face",
            "phoneNumber",
            "email",
            "activity","Description");
    name = "CompanyName";
    imageURL = "image";
//    id = 0l;
    userId = 0l;
  }


  public CompanyBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CompanyBuilder withAddress(Address address) {
    this.address = address;
    return this;
  }


  public CompanyBuilder withImageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }


  public CompanyBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public CompanyBuilder withUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public Company build() {
    Company company = new Company(id, address, companyInformation, imageURL, userId);
    setToDefault();
    return company;
  }

}
