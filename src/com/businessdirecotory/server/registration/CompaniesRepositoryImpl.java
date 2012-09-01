package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.code.twig.FindCommand;
import com.google.code.twig.ObjectDatastore;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class CompaniesRepositoryImpl implements CompaniesRepository {

  private DatastoreService service;

  private CompanyEntityMapper companyEntityMapper;
  private ObjectDatastore datastore;

//  @Inject
//  public CompaniesRepositoryImpl(DatastoreService service, CompanyEntityMapper companyEntityMapper) {
//    this.service = service;
//    this.companyEntityMapper = companyEntityMapper;                                           /
//  }

  @Inject
  public CompaniesRepositoryImpl(ObjectDatastore datastore) {

    this.datastore = datastore;
  }

  @Override
  public Long add(Company company) {

    company.setKeyWords(getKeywords(company));
    Key key = datastore.store(company);
    return key.getId();

  }

  private Set<String> getKeywords(Company company) {
    Set<String> keyword = new TreeSet<String>();
    putInKeywordsSet(keyword, getTextWords(company.getAddress().getCity()));
    putInKeywordsSet(keyword, getTextWords(company.getAddress().getCounty()));
    putInKeywordsSet(keyword, getTextWords(company.getAddress().getStreet()));
    putInKeywordsSet(keyword, getTextWords(company.getAddress().getDescription()));
    putInKeywordsSet(keyword, getTextWords(company.getAddress().getCity()));
    putInKeywordsSet(keyword, getTextWords(company.getInfo().getActivity()));
    putInKeywordsSet(keyword, getTextWords(company.getInfo().getCompanyName()));
    putInKeywordsSet(keyword, getTextWords(company.getInfo().getEmail()));
    putInKeywordsSet(keyword, getTextWords(company.getInfo().getContactFace()));
    putInKeywordsSet(keyword, getTextWords(company.getInfo().getDescription()));
    return keyword;
  }


  @Override
  public List<Company> getByKeyWord(String search) {
    List<String> words = getTextWords(search.toLowerCase());
    FindCommand.RootFindCommand<Company> findCommand = datastore.find().type(Company.class);
    for (String word : words) {
      findCommand.addFilter("index", Query.FilterOperator.EQUAL, search);
    }
    return execute(findCommand);
  }

  private List<Company> execute(FindCommand.RootFindCommand<Company> findCommand) {
    Iterator<Company> companies = findCommand.now();

    List<Company> result = new ArrayList<Company>();

    while (companies.hasNext()) {
      result.add(companies.next());
    }
    return result;
  }

  @Override
  public Company getById(long id) {
    return datastore.load().type(Company.class).id(id).now();
  }

  @Override
  public Company getByUserId(long userId) {
    return datastore.find().type(Company.class).addFilter("userId", Query.FilterOperator.EQUAL, userId).returnUnique().now();
  }

  @Override
  public void update(Company company) {
    datastore.associate(company);
    datastore.update(company);
  }

  private List<String> getTextWords(String keyword) {

    List<String> words = new ArrayList<String>();
    if (keyword != null) {
      Pattern pattern = Pattern.compile("[а-я0-9А-Яa-zA-Z_]+");
      Matcher matcher = pattern.matcher(keyword);
      while (matcher.find()) {
        words.add(keyword.substring(matcher.start(), matcher.end()).toLowerCase());
      }
    }
    return words;
  }

  private void putInKeywordsSet(Set<String> keywordsList, List<String> keywords) {
    for (String string : keywords) {
      keywordsList.add(string);
    }
  }

}
