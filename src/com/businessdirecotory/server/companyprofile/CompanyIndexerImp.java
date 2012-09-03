package com.businessdirecotory.server.companyprofile;

import com.businessdirecotory.shared.entites.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CompanyIndexerImp implements CompanyIndexer{

  public Set<String> getKeywords(Company company) {
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

  private void putInKeywordsSet(Set<String> keywordsList, List<String> keywords) {
    for (String string : keywords) {
      keywordsList.add(string);
    }
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

}
