package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.shared.entites.Company;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyBuilder {


  private String name="";
  private String location="";
  private String address="";
  private String email="";
  private String activity="";
  private String description="";
  private String phoneNumber="";
  private String contactFace="";
  private String imageURL = "";
  private Long id;
  private String facebookLink="";
  private String googlePlusLink="";
  private String twitterLink="";
  private Long userId;


  public CompanyBuilder() {
    setToDefault();
  }

  private void setToDefault() {
    id = 0l;
    name = "company";
    location = "location";
    address = "address";
    email = "mail@mail.com";
    activity = "activity";
    description = "description";
    phoneNumber = "08888888";
    contactFace = "contactFace";
    userId = 0l;
  }


  public CompanyBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CompanyBuilder withLocation(String location) {
    this.location = location;
    return this;
  }

  public CompanyBuilder withAddress(String address) {
    this.address = address;
    return this;
  }

  public CompanyBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public CompanyBuilder withActivity(String activity) {
    this.activity = activity;
    return this;
  }

  public CompanyBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  public CompanyBuilder withPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public CompanyBuilder withContactFace(String contactFace) {
    this.contactFace = contactFace;
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


  public Company build() {
    Company company = new Company(id, name, location, address, email, activity,
            description, phoneNumber, contactFace, imageURL, facebookLink, googlePlusLink, twitterLink, userId);
    setToDefault();
    return company;
  }


  public CompanyBuilder withFacebook(String facebookLink) {
    this.facebookLink = facebookLink;
    return this;
  }

  public CompanyBuilder withGooglePlus(String googlePlusLink) {
    this.googlePlusLink = googlePlusLink;
    return this;
  }

  public CompanyBuilder withTwitter(String twitterLink) {
    this.twitterLink = twitterLink;
    return this;
  }

  public CompanyBuilder withUserId(Long userId) {
    this.userId = userId;
    return this;
  }
}
