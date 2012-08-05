package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;

import java.util.ArrayList;
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

  @Inject
  public CompaniesRepositoryImpl(DatastoreService service, CompanyEntityMapper companyEntityMapper) {
    this.service = service;
    this.companyEntityMapper = companyEntityMapper;
  }

  @Override
  public Long add(Company company) {
    Entity companyEntity;
    if (company.getId() == null || company.getId() == 0l) {
      companyEntity = new Entity(CompanyEntity.KIND);
    } else {
      Key key = KeyFactory.createKey(CompanyEntity.KIND, company.getId());
      try {
        companyEntity = service.get(key);
      } catch (EntityNotFoundException e) {
        companyEntity = new Entity(CompanyEntity.KIND);
      }
    }

    companyEntity = companyEntityMapper.createEntity(company, companyEntity);

    Set<String> keyword = getKeywords(company);

    companyEntity.setProperty(CompanyEntity.INDEX, keyword);

    service.put(companyEntity);
    return companyEntity.getKey().getId();
  }

  private Set<String> getKeywords(Company company) {
    Set<String> keyword = new TreeSet<String>();

    putInKeywordsSet(keyword, getTextWords(company.getName()));
    putInKeywordsSet(keyword, getTextWords(company.getAddress()));
    putInKeywordsSet(keyword, getTextWords(company.getActivity()));
    putInKeywordsSet(keyword, getTextWords(company.getLocation()));
    putInKeywordsSet(keyword, getTextWords(company.getDescription()));
    putInKeywordsSet(keyword, getTextWords(company.getEmail()));
    putInKeywordsSet(keyword, getTextWords(company.getContactFace()));
    return keyword;
  }


  @Override
  public List<Company> getByKeyWord(String keyword) {

    Query query = new Query(CompanyEntity.KIND);

    List<Query.Filter> filters = new ArrayList<Query.Filter>();


    List<Company> companies = new ArrayList<Company>();

    List<String> words = getTextWords(keyword);
    if (words.size() <= 1) {
      query.setFilter(new Query.FilterPredicate(CompanyEntity.INDEX, Query.FilterOperator.EQUAL, words.get(0)));
    } else {
      for (String string : words) {
        filters.add(new Query.FilterPredicate(CompanyEntity.INDEX, Query.FilterOperator.EQUAL, string));
      }
      query.setFilter(Query.CompositeFilterOperator.or(filters));

    }
    Iterable<Entity> returnedEntities = service.prepare(query).asIterable();

    for (Entity entity : returnedEntities) {
      companies.add(companyEntityMapper.create(entity));
    }
    return companies;
  }

  @Override
  public Company getByEmail(String email) {

    Query query = new Query(CompanyEntity.KIND);

    query.setFilter(new Query.FilterPredicate(CompanyEntity.EMAIL, Query.FilterOperator.EQUAL, email));

    Entity entity = service.prepare(query).asSingleEntity();

    if (entity != null) {
      return companyEntityMapper.create(entity);
    }
    return null;
  }

  @Override
  public Company getById(long id) {

    Key key = KeyFactory.createKey(CompanyEntity.KIND, id);
    try {
      Entity entity = service.get(key);
      return companyEntityMapper.create(entity);
    } catch (EntityNotFoundException e) {
      return null;
    }


  }

  @Override
  public Company getByUserId(long userId) {
    Query query = new Query(CompanyEntity.KIND);
    query.setFilter(new Query.FilterPredicate(CompanyEntity.USER_ID, Query.FilterOperator.EQUAL, userId));
    try {
      Entity entity = service.prepare(query).asSingleEntity();
      if (entity != null) {
        return companyEntityMapper.create(entity);
      }
    } catch (PreparedQuery.TooManyResultsException e) {
      System.out.println("There where more than one company associated with used: " + userId);
    }
    return null;
  }


  private List<String> getTextWords(String keyword) {
    List<String> words = new ArrayList<String>();
    if (keyword != null) {
      Pattern pattern = Pattern.compile("[а-я0-9А-Яa-zA-Z_]+");
      Matcher matcher = pattern.matcher(keyword);
      while (matcher.find()) {
        words.add(keyword.substring(matcher.start(), matcher.end()));
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
