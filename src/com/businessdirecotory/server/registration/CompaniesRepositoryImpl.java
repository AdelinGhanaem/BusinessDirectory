package com.businessdirecotory.server.registration;

import com.businessdirecotory.shared.entites.Company;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterator;
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

    company.setIndex(getKeywords(company));
    Key key = datastore.store(company);
//    Entity companyEntity;
//    if (company.getId() == null || company.getId() == 0l) {
//      companyEntity = new Entity(CompanyEntity.KIND);
//    } else {
//      Key key = KeyFactory.createKey(CompanyEntity.KIND, company.getId());
//      try {
//        companyEntity = service.get(key);
//      } catch (EntityNotFoundException e) {
//        companyEntity = new Entity(CompanyEntity.KIND);
//      }
//    }
//
//    companyEntity = companyEntityMapper.createEntity(company, companyEntity);
//
//    Set<String> keyword = getKeywords(company);
//
//    companyEntity.setProperty(CompanyEntity.INDEX, keyword);
//
//    service.put(companyEntity);
//    return companyEntity.getKey().getId();
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
//    Query query = new Query(CompanyEntity.KIND);
//
//    List<Query.Filter> filters = new ArrayList<Query.Filter>();
//
//
//    List<Company> companies = new ArrayList<Company>();
//
//    if (words.size() <= 1) {
//      query.setFilter(new Query.FilterPredicate(CompanyEntity.INDEX, Query.FilterOperator.EQUAL, words.get(0)));
//    } else {
//      for (String string : words) {
//        filters.add(new Query.FilterPredicate(CompanyEntity.INDEX, Query.FilterOperator.EQUAL, string));
//      }
//      query.setFilter(Query.CompositeFilterOperator.or(filters));
//
//    }
//    Iterable<Entity> returnedEntities = service.prepare(query).asIterable();
//
//    for (Entity entity : returnedEntities) {
//      companies.add(companyEntityMapper.create(entity));
//    }
  }

  private List<Company> execute(FindCommand.RootFindCommand<Company> findCommand) {
    Iterator<Company> companies = findCommand.now();

    List<Company> result = new ArrayList<Company>();

    while (companies.hasNext()) {
      result.add(companies.next());
    }
    return result;
  }
//
//  @Override
//  public Company getByEmail(String email) {
//
//    Query query = new Query(CompanyEntity.KIND);
//
//    query.setFilter(new Query.FilterPredicate(CompanyEntity.EMAIL, Query.FilterOperator.EQUAL, email));
//
//    Entity entity = service.prepare(query).asSingleEntity();
//
//    if (entity != null) {
//      return companyEntityMapper.create(entity);
//    }
//    return null;
//  }

  @Override
  public Company getById(long id) {
    return datastore.load().type(Company.class).id(id).now();
  }

  @Override
  public Company getByUserId(long userId) {
    QueryResultIterator<Company> companies = datastore.find().type(Company.class).addFilter("userId", Query.FilterOperator.EQUAL, userId).now();
    return companies.next();

  }

  @Override
  public void update(Company company) {
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
