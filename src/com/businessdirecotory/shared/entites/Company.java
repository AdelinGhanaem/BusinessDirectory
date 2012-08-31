package com.businessdirecotory.shared.entites;


import java.io.Serializable;
import java.util.Set;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Company implements Serializable {

  private Long id;

  private Address address;

  private CompanyInformation info;

  private String logoURL;

  private Long userId;

  private Set<String> index;

  public Set<String> getIndex() {
    return index;
  }

  public void setIndex(Set<String> index) {
    this.index = index;
  }

  public Company() {
  }

  public Company(Long id,
                 Address address,
                 CompanyInformation info,
                 String logoURL,
                 Long userId) {
    this.id = id;
    this.address = address;
    this.info = info;
    this.logoURL = logoURL;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public CompanyInformation getInfo() {
    return info;
  }

  public void setInfo(CompanyInformation info) {
    this.info = info;
  }

  public String getLogoURL() {
    return logoURL;
  }

  public void setLogoURL(String logoURL) {
    this.logoURL = logoURL;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
