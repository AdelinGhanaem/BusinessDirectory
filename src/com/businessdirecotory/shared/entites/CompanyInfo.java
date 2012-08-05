package com.businessdirecotory.shared.entites;

import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompanyInfo implements Serializable {
  private Long id;

  public CompanyInfo(long id) {

    this.id = id;
  }

  public CompanyInfo() {
  }

  public Long getId() {
    return id;
  }
}
