package com.businessdirecotory.shared.entites;

import com.google.code.twig.annotation.Id;
import com.google.code.twig.annotation.Parent;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Address implements Serializable {

//  @Child
  private LatLong latLong;

  private String county;

  private String city;

  private String street;

  private String description;

  @Id
  private long id;

  @Parent
  private Company company;

  public Address(LatLong latLong, String county, String city, String street, String description) {
    this.latLong = latLong;
    this.county = county;
    this.city = city;
    this.street = street;
    this.description = description;
  }

  public Address() {
  }

  public LatLong getLatLong() {
    return latLong;
  }

  public String getCounty() {
    return county;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  public String getDescription() {
    return description;
  }


  public void setLatLong(LatLong latLong) {
    this.latLong = latLong;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;

  }


}
