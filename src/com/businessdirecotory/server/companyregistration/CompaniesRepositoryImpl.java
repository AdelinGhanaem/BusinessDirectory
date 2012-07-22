package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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

  CompanyBuilder builder = new CompanyBuilder();

  @Inject
  public CompaniesRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  @Override
  public void save(Company company) {
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

    companyEntity = setEntityProperties(company, companyEntity);

    Set<String> keyword = getKeywords(company);

    companyEntity.setProperty(CompanyEntity.INDEX, keyword);

    service.put(companyEntity);
  }

  private Entity setEntityProperties(Company company, Entity companyEntity) {
    companyEntity.setProperty(CompanyEntity.NAME, company.getName());
    companyEntity.setProperty(CompanyEntity.ADDRESS, company.getAddress());
    companyEntity.setProperty(CompanyEntity.ACTIVITY, company.getActivity());
    companyEntity.setProperty(CompanyEntity.LOCATION, company.getLocation());
    companyEntity.setProperty(CompanyEntity.EMAIL, company.getEmail());
    companyEntity.setProperty(CompanyEntity.PASSWORD, company.getPassword());
    companyEntity.setProperty(CompanyEntity.CONTACT_FACE, company.getContactFace());
    companyEntity.setProperty(CompanyEntity.PHONE_NUMBER, company.getPhoneNumber());
    companyEntity.setProperty(CompanyEntity.DESCRIPTION, company.getDescription());

    return companyEntity;
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
  public boolean isRegistered(Account account) {

    Query query = new Query(CompanyEntity.KIND);

    query.setFilter(Query.CompositeFilterOperator.and(
            new Query.FilterPredicate(CompanyEntity.EMAIL, Query.FilterOperator.EQUAL, account.getUsername()),
            new Query.FilterPredicate(CompanyEntity.PASSWORD, Query.FilterOperator.EQUAL, account.getPassword())
    ));

    Entity companyEntity = service.prepare(query).asSingleEntity();

    return companyEntity != null;

  }

  @Override
  public List<Company> getByKeyWord(String keyword) {

    Query query = new Query(CompanyEntity.KIND);

    List<Query.Filter> filters = new ArrayList<Query.Filter>();


    List<Company> companies = new ArrayList<Company>();

    List<String> words = getTextWords(keyword);
    if (words.size() == 1) {
      query.setFilter(new Query.FilterPredicate(CompanyEntity.INDEX, Query.FilterOperator.EQUAL, words.get(0)));
    } else {
      for (String string : words) {
        filters.add(new Query.FilterPredicate(CompanyEntity.INDEX, Query.FilterOperator.EQUAL, string));
      }
      query.setFilter(Query.CompositeFilterOperator.or(filters));

    }


    Iterable<Entity> returnedEntities = service.prepare(query).asIterable();


    for (Entity entity : returnedEntities) {
      companies.add(create(entity));
    }
    return companies;
  }

  @Override
  public Company getByEmail(String email) {

    Query query = new Query(CompanyEntity.KIND);

    query.setFilter(new Query.FilterPredicate(CompanyEntity.EMAIL, Query.FilterOperator.EQUAL, email));

    Entity entity = service.prepare(query).asSingleEntity();

    if (entity != null) {
      return create(entity);

    }
    return null;
  }

  @Override
  public Company getById(long id) {

    Key key = KeyFactory.createKey(CompanyEntity.KIND, id);
    try {
      Entity entity = service.get(key);
      return create(entity);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  private Company create(Entity entity) {
    return builder.withId(entity.getKey().getId()).withName(String.valueOf(entity.getProperty(CompanyEntity.NAME)))
            .withActivity(String.valueOf(entity.getProperty(CompanyEntity.ACTIVITY)))
            .withLocation(String.valueOf(entity.getProperty(CompanyEntity.LOCATION)))
            .withContactFace(String.valueOf(entity.getProperty(CompanyEntity.CONTACT_FACE)))
            .withAddress(String.valueOf(entity.getProperty(CompanyEntity.ADDRESS)))
            .withEmail(String.valueOf(entity.getProperty(CompanyEntity.EMAIL)))
            .withPhoneNumber(String.valueOf(entity.getProperty(CompanyEntity.PHONE_NUMBER)))
            .withDescription(String.valueOf(entity.getProperty(CompanyEntity.DESCRIPTION)))
            .build();
  }

  private List<String> getTextWords(String keyword) {
    List<String> words = new ArrayList<String>();
    if (keyword != null) {
      Pattern pattern = Pattern.compile("[а-яА-Яa-zA-Z_]+");
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
