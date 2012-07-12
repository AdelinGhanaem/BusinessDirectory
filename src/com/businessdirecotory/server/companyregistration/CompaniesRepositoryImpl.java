package com.businessdirecotory.server.companyregistration;

import com.businessdirecotory.client.authorization.Account;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.CompanyBuilder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.impl.EntityCodex;

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

  @Inject
  public CompaniesRepositoryImpl(DatastoreService service) {
    this.service = service;
  }

  @Override
  public void save(Company company) {

    Entity companyEntity = new Entity(CompanyEntity.KIND);
    companyEntity.setProperty(CompanyEntity.NAME, company.getName());
    companyEntity.setProperty(CompanyEntity.ADDRESS, company.getAddress());
    companyEntity.setProperty(CompanyEntity.ACTIVITY, company.getActivity());
    companyEntity.setProperty(CompanyEntity.LOCATION, company.getLocation());
    companyEntity.setProperty(CompanyEntity.EMAIL, company.getEmail());
    companyEntity.setProperty(CompanyEntity.PASSWORD, company.getPassword());
    companyEntity.setProperty(CompanyEntity.CONTACT_FACE, company.getContactFace());

    Set<String> keyword = new TreeSet<String>();

    putInKeywordsSet(keyword, getTextWords(company.getName()));
    putInKeywordsSet(keyword, getTextWords(company.getAddress()));
    putInKeywordsSet(keyword, getTextWords(company.getActivity()));
    putInKeywordsSet(keyword, getTextWords(company.getLocation()));
    putInKeywordsSet(keyword, getTextWords(company.getDescription()));
    putInKeywordsSet(keyword, getTextWords(company.getEmail()));
    putInKeywordsSet(keyword, getTextWords(company.getContactFace()));
    companyEntity.setProperty(CompanyEntity.INDEX, keyword);

    service.put(companyEntity);
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
        filters.add(new Query.FilterPredicate("index", Query.FilterOperator.EQUAL, string));
      }
      query.setFilter(Query.CompositeFilterOperator.or(filters));

    }


    Iterable<Entity> returnedEntities = service.prepare(query).asIterable();

    CompanyBuilder builder = new CompanyBuilder();

    for (Entity entity : returnedEntities) {
      companies.add(builder
              .withName(String.valueOf(entity.getProperty(CompanyEntity.NAME)))
              .withActivity(String.valueOf(entity.getProperty(CompanyEntity.ACTIVITY)))
              .withLocation(String.valueOf(entity.getProperty(CompanyEntity.LOCATION)))
              .withContactFace(String.valueOf(entity.getProperty(CompanyEntity.CONTACT_FACE)))
              .withAddress(String.valueOf(entity.getProperty(CompanyEntity.ADDRESS)))
              .withEmail(String.valueOf(entity.getProperty(CompanyEntity.EMAIL)))
              .build());

    }
    return companies;
  }

  private List<String> getTextWords(String keyword) {
    List<String> words = new ArrayList<String>();
    Pattern pattern = Pattern.compile("[а-яА-Яa-zA-Z_]+");
    Matcher matcher = pattern.matcher(keyword);
    while (matcher.find()) {
      words.add(keyword.substring(matcher.start(), matcher.end()));
    }
    return words;
  }

  private void putInKeywordsSet(Set<String> keywordsList, List<String> keywords) {
    for (String string : keywords) {
      keywordsList.add(string);
    }
  }

}
