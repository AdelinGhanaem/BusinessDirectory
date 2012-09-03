package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.Company;

import java.util.Set;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface CompanyIndexer {

  public Set<String> getKeywords(Company company);

}
