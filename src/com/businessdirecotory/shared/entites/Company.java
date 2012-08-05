package com.businessdirecotory.shared.entites;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Company implements Serializable {


  private Long id;

  private long userId;

  @NotEmpty(message = "Запишете името на команията")
  private String name = "";

  @NotEmpty(message = "Изберете Град")
  private String location = "";

  @NotEmpty(message = "Посочете адрес на компанията")
  private String address = "";

  @Email(message = "Посочете адрес на компанията")
  private String email = "";

  @NotEmpty(message = "Посочете вид дейност")
  private String activity = "";

  private String description = "";

  private String phoneNumber = "";

  private String contactFace = "";

  private String logoURL = "";

  private String facebook = "";

  private String googlePlus = "";

  private String twitter = "";




  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public void setGooglePlus(String googlePlus) {
    this.googlePlus = googlePlus;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }

  public String getFacebook() {
    return facebook;
  }

  public String getGooglePlus() {
    return googlePlus;
  }

  public String getTwitter() {
    return twitter;
  }



  public Company() {
  }

  public Company(Long id, String name, String location, String address, String email, String activity,
                 String description,
                 String phoneNumber, String contactFace,
                 String logoURL, String facebook,
                 String googlePlus,
                 String twitter,Long userId) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.address = address;
    this.email = email;
    this.activity = activity;
    this.description = description;
    this.phoneNumber = phoneNumber;
    this.contactFace = contactFace;
    this.logoURL = logoURL;
    this.facebook = facebook;
    this.googlePlus = googlePlus;
    this.twitter = twitter;
    this.userId = userId;
  }

  public String getLogoURL() {
    return logoURL;
  }

  public void setName(String name) {
    this.name = name;
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


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public void setLogoURL(String logoURL) {

    this.logoURL = logoURL;
  }

  public void setInfoId(Long infoId) {

  }


  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getUserId() {
    return userId;
  }
}
