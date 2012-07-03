package com.businessdirecotory.shared.entites;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Company implements Serializable {

  @NotNull
  private String name;
  @NotNull
  private String location;
  @NotNull
  private String address;
  @NotNull
  private String email;
  @NotNull
  private String activity;
  @NotNull
  private String description;
  @NotNull
  private String phoneNumber;
  @NotNull
  private String contactFace;


  public Company() {
  }


  public void setName(String name) {
    this.name = name;
  }

  public Company(String name, String location, String address, String email, String activity, String description, String phoneNumber, String contactFace) {
    this.name = name;
    this.location = location;
    this.address = address;
    this.email = email;
    this.activity = activity;
    this.description = description;
    this.phoneNumber = phoneNumber;
    this.contactFace = contactFace;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setContactFace(String contactFace) {
    this.contactFace = contactFace;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public String getEmail() {
    return email;
  }

  public String getActivity() {
    return activity;
  }

  public String getDescription() {
    return description;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }


  public String getContactFace() {
    return contactFace;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
