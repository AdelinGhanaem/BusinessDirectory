package com.businessdirecotory.shared.entites.actions;

import com.businessdirecotory.server.companyregistration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyBuilder {


  private String name;
  private String location;
  private String address;
  private String email;
  private String activity;
  private String description;
  private String phoneNumber;
  private String contactFace;
  private CompaniesRepository repository;
  private String password;


  public CompanyBuilder() {
    name = "company";
    location = "location";
    address = "address";
    email = "mail@mail.com";
    activity = "activity";
    description = "description";
    phoneNumber = "08888888";
    contactFace = "contactFace";
    password="123123";
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

  public CompanyBuilder withPassword(String password) {

    this.password = password;
    return this;
  }


  public Company build() {
    return new Company(name, location, address, email, activity, description, phoneNumber, contactFace, password);
  }


}
