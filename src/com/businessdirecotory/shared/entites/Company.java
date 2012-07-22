package com.businessdirecotory.shared.entites;


import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Company implements Serializable {



  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String name;

  private String location;

  private String address;

  private String email;

  private String activity;

  private String description;

  private String phoneNumber;

  private String contactFace;

  private String password;

  public Company() {
  }

  public Company(Long id,String name, String location, String address, String email, String activity,
                 String description, String phoneNumber, String contactFace, String password) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.address = address;
    this.email = email;
    this.activity = activity;
    this.description = description;
    this.phoneNumber = phoneNumber;
    this.contactFace = contactFace;
    this.password = password;
  }


  public void setName(String name) {
    this.name = name;
  }

  public Company(String name, String location, String address, String email, String activity, String description, String phoneNumber, String contactFace) {

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
