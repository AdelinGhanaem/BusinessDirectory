package com.businessdirecotory.shared.entites;

import com.google.code.twig.annotation.Id;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class LatLong implements Serializable {

  private Double latitude;

  private Double longitude;

  @Id
  private Long id;

  public LatLong() {
  }

  public LatLong(Double latitude, Double longitude) {

    this.latitude = latitude;

    this.longitude = longitude;

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
}
