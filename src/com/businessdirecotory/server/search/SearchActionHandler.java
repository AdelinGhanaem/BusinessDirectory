package com.businessdirecotory.server.search;

import com.businessdirecotory.server.registration.CompaniesRepository;
import com.businessdirecotory.shared.entites.Company;
import com.businessdirecotory.shared.entites.actions.SearchAction;
import com.businessdirecotory.shared.entites.reponses.SearchResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SearchActionHandler implements ActionHandler<SearchAction, SearchResponse> {

  private CompaniesRepository repository;

  @Inject
  public SearchActionHandler(CompaniesRepository repository) {
    this.repository = repository;
  }

  @Override
  public SearchResponse handle(SearchAction action) {

    List<Company> companyList = repository.getByKeyWord(action.getKeyWord());

//    String string = "Jovan Vladimir (died 1016) was ruler of Duklja, the most powerful Serbian " +
//            "principality of the time, from around 1000 to 1016. He ruled during the protracted war " +
//            "between the Byzantine Empire and the First Bulgarian Empire. His close relationship with" +
//            " Byzantium did not save Duklja from the expansionist Tsar Samuel of Bulgaria, who " +
//            "conquered the principality in around 1010 and took Jovan Vladimir prisoner. " +
//            "A medieval chronicle asserts that Samuel's daughter, Theodora Kosara, fell in love with Vladimir and begged her father for his hand. The tsar allowed the marriage and returned Duklja to Vladimir, who ruled as his vassal. Vladimir was acknowledged as a pious, just, and peaceful ruler. He took no part in his father-in-law's war efforts. The warfare culminated with Samuel's defeat by the Byzantines in 1014; the tsar died soon afterward. In 1016 Vladimir fell victim to a plot by Ivan Vladislav, the last ruler of the First Bulgarian Empire. He was beheaded in front of a church in Prespa, the empire's capital, and was buried there. He was soon recognized as a martyr and saint; his feast day is celebrated on 22 May";
//    List<String> strings = new ArrayList<String>();
//    Pattern pattern = Pattern.compile("[а-я0-9А-Яa-zA-Z_]+");
//    Matcher matcher = pattern.matcher(string);
//    while (matcher.find()) {
//      strings.add(string.substring(matcher.start(), matcher.end()));
//    }
//    CompanyBuilder builder = new CompanyBuilder();
    return new SearchResponse(new ArrayList<Company>(companyList));
  }
}
