package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;

import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface CompaniesRepository {
  /**
   * Saves company in data store
   *
   * @param company:the company to be saved;
   */
  Long add(Company company);

  List<Company> getByKeyWord(String keyword);

//  Company getByEmail(String email);

  Company getById(long id);


  Company getByUserId(long userId);

  void update( Company company);
}
