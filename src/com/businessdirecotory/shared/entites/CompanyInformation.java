package com.businessdirecotory.shared.entites;

import com.google.code.twig.annotation.Id;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyInformation implements Serializable {

  private String activity;
  private String companyName;
  private String contactFace;
  private String phoneNumber;

  @Id
  private String email;
  private String description;

  private Company company;


  public CompanyInformation(String companyName,
                            String contactFace,
                            String phoneNumber,
                            String email,
                            String activity,
                            String description) {
    this.activity = activity;
    this.companyName = companyName;
    this.contactFace = contactFace;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.description = description;
  }

  public CompanyInformation() {
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getContactFace() {
    return contactFace;
  }

  public String getDescription() {
    return description;
  }

  public Company getCompany() {
    return company;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setContactFace(String contactFace) {
    this.contactFace = contactFace;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
