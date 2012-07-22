package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.authorization.Account;
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
  void save(Company company);

  boolean isRegistered(Account account);

  List<Company> getByKeyWord(String keyword);

  Company getByEmail(String email);

  Company getById(long id);
}
