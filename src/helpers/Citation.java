package helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.AboveIndexException;

/**
 * 
 * This class handles all citations referenced in the output, whether it be in
 * the top left table or a paper citation.
 * 
 * @author Ronald
 *
 */
public class Citation {

  private static ArrayList<Citation> citationCollectionAll =
      new ArrayList<Citation>();
  private static ArrayList<Citation> citationCollection2009 =
      new ArrayList<Citation>();
  private static ArrayList<Citation> paperCitationCollection =
      new ArrayList<Citation>();
  private String type;
  private Integer amount;

  /**
   * 
   * The constructor which creates a citation of type citation, h-index or i-10
   * index.
   * 
   * @param type the type of citation, be it Paper citations, All, or i10-index
   *        etc.
   * @param amount the value associated with the citation, the amount of
   *        citations of this type.
   */
  private Citation(String type, Integer amount) {
    this.type = type;
    this.amount = amount;
  }

  /**
   * 
   * The constructor which creates a citation of type Paper Citation, referred
   * to outside of the 2x3 box, when referencing the papers.
   * 
   * @param amount the value associated with the citation, the amount of
   *        citations of this type.
   */
  private Citation(Integer amount) {
    // For a paper citation, there will be no type retrieved, so we define it
    // as a paper citation.
    this.type = "Paper Citation";
    this.amount = amount;
  }

  /**
   * 
   * This factory method creates a citation to put in the all citations
   * collection.
   * 
   * @param type, the type of the citation, in this case Citation.
   * @param amount the value associated with the citation, the amount of
   *        citations of this type.
   */
  private static void createCitationAll(String type, Integer amount) {
    // If this citation is part of the all time column, put it into this
    // collection.
    citationCollectionAll.add(new Citation(type, amount));
  }

  /**
   * 
   * This factory method creates a citation to put in the 2009 citations
   * collection.
   * 
   * @param type, the type of the citation, in this case i10-index.
   * @param amount the value associated with the citation, the amount of
   *        citations of this type.
   */
  private static void createCitation2009(String type, Integer amount) {
    // If this citation is part of the 2009 time column, put it into this
    // collection.
    citationCollection2009.add(new Citation(type, amount));
  }

  /**
   * 
   * This factory method creates a citation to put in the paper citations
   * collection.
   * 
   * @param type, the type of the citation, in this case Paper Citation.
   * @param amount the value associated with the citation, the amount of
   *        citations of this type.
   */
  private static void createPaperCitation(Integer amount) {
    // If the citation is part of the publications box, it is a paper citation.
    paperCitationCollection.add(new Citation(amount));
  }

  /**
   * 
   * This method resets the collections. This is used when looking at new
   * authors and urls.
   * 
   */
  private static void resetArrayLists() {
    // Resets collections relative to each new author.
    citationCollectionAll = new ArrayList<Citation>();
    citationCollection2009 = new ArrayList<Citation>();
    paperCitationCollection = new ArrayList<Citation>();
  }

  /**
   * 
   * This method gets the type of the citation.
   * 
   * @return the type of the citation.
   */
  public String getType() {
    return type;
  }

  /**
   * 
   * This method gets the amount of the citation.
   * 
   * @return the amount of the citation.
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * 
   * This method gets the amount of citations in the all collection at index 0.
   * This represents the amount of citations for all time.
   * 
   * @return the amount of citations in the all collection at index 0.
   */
  public static String getAllCitations() {
    // This retrieves the number of citations for all time, and converts it to
    // a string.
    return citationCollectionAll.get(0).getAmount().toString();
  }

  /**
   * 
   * This method gets the amount of citations in the 2009 collection at index 2.
   * This represents the i-10 index after 2009.
   * 
   * @return the amount of citations in the 2009 collection at index 2.
   */
  public static String geti10IndexAfter2009() {
    // This retrieves the number of i-10indexes after 2009, and converts it to
    // a string.
    return citationCollection2009.get(2).getAmount().toString();
  }

  /**
   * 
   * This method looks at the first X papers, where X is the parameter amount,
   * and adds the total amount of citations across each paper, and returns the
   * total.
   * 
   * @param amount the amount of papers to retrieve citations from.
   * @return the total amount of citations from some amount of papers.
   * @throws AboveIndexException when the amount is greater than the amount of
   *         citations.
   */
  public static Integer getPaperCitations(Integer amount)
      throws AboveIndexException {
    // This will be the sum of all citations in the publication table.
    Integer total = 0;
    try {
      // This checks of amount is greater than the number of possible indexes.
      if (amount <= paperCitationCollection.size()) {
        for (Integer i = 0; i < amount; i++) {
          // Adds the paper citation value to the total, for every item in the
          // Collection up to the amount index.
          total += paperCitationCollection.get(i).getAmount();
        }
      } else {
        throw new AboveIndexException();
      }
    } catch (AboveIndexException e) {
      // If amount is higher than the amount of indexes in the collection, just
      // collect the total possible number for the total.
      for (Citation citation : paperCitationCollection) {
        total += citation.getAmount();
      }
    }
    return total;
  }

  /**
   * 
   * This method searches through the html for the url and retrieves the total
   * citations, h-index and i-10index for alltime and 2009. These values get
   * added to their respective collections. In addition, the citations for the
   * papers are collected.
   * 
   * @param googleScholarURL the URL in string form to be returned.
   * @throws Exception when the URL does not exist.
   */
  public static void populateCitations(String googleScholarURL)
      throws Exception {
    // This resets the collections to allow values for new authors to be
    // correct.
    resetArrayLists();
    // Also populate paper citations, a similar regex method below.
    populatePaperCitations(googleScholarURL);
    // The raw html from the url.
    String rawHTMLString = helpers.HTML.getHTML(googleScholarURL);
    // The two regexes to find the amounts for each citation type, and the
    // citation types themselves.
    String reForAmountExtraction =
        "<td class=\"cit-borderleft cit-data\">([0-9]*?)</td>";
    String reForTypeExtraction =
        "<a href=\"(.*?)\" class=\"cit-dark-link\" " + "onclick=\"(.*?)\" "
            + "title=\"(.*?)\">(.*?)</a>";
    // Using these regexes, we populate an array of types and the amounts
    // Associated with them.
    ArrayList<String> types = getTypes(rawHTMLString, reForTypeExtraction);
    ArrayList<Integer> amounts =
        getAmounts(rawHTMLString, reForAmountExtraction);
    Integer j = 0;
    for (int i = 0; i < types.size(); i++) {
      // For each type, we create the proper citation for all time and for 2009
      // Where i will represent a different type for each value of i.
      if (i == 0) {
        createCitationAll(types.get(i), amounts.get(j++));
        createCitation2009(types.get(i), amounts.get(j++));
      } else if (i == 1) {
        createCitationAll(types.get(i), amounts.get(j++));
        createCitation2009(types.get(i), amounts.get(j++));
      } else if (i == 2) {
        createCitationAll(types.get(i), amounts.get(j++));
        createCitation2009(types.get(i), amounts.get(j++));
      }
    }
  }

  /**
   * 
   * This method searches through the html for the url and retrieves the
   * citations associated with each paper, and adds these values to the proper
   * collection.
   * 
   * @param googleScholarURL the URL in string form to be returned.
   * @throws Exception when the URL does not exist.
   */
  private static void populatePaperCitations(String googleScholarURL)
      throws Exception {
    // The raw html from the url.
    String rawHTMLString = helpers.HTML.getHTML(googleScholarURL);
    // The regex to fetch publication citations.
    String reForTitleExtraction =
        "<a class=\"cit-dark-link\" href=\"(.*?)cites=(.*?)\">([0-9]*?)</a>";
    // Use a pattern and matcher to search for valid values of the regex.
    Pattern patternObject = Pattern.compile(reForTitleExtraction);
    Matcher matcherObject = patternObject.matcher(rawHTMLString);
    // When a match is found, create the paper citation with the value found.
    while (matcherObject.find()) {
      createPaperCitation(Integer.valueOf(matcherObject.group(3)));
    }
  }

  /**
   * 
   * This method finds all viable types of citations in the chart for the given
   * url, and returns an array of these types.
   * 
   * @param allText the raw html string associated with the url.
   * @param regex the regular expression used to find the types of available
   *        citations.
   * @return an array of these citation types.
   */
  private static ArrayList<String> getTypes(String allText, String regex) {
    // Create the collection to be returned.
    ArrayList<String> types = new ArrayList<String>();
    // Use a pattern and matcher to find values associated with the regex given
    // as a parameter.
    Pattern patternObject = Pattern.compile(regex);
    Matcher matcherObject = patternObject.matcher(allText);
    // If found, add the string to the collection.
    while (matcherObject.find()) {
      types.add(matcherObject.group(4));
    }
    return types;
  }

  /**
   * 
   * This method finds all viable values of citations in the chart for the given
   * url, and returns an array of these values.
   * 
   * @param allText the raw html string associated with the url.
   * @param regex the regular expression used to find the values of available
   *        citations.
   * @return an array of these citation values.
   */
  private static ArrayList<Integer> getAmounts(String allText, String regex) {
    // Create the collection to be returned.
    ArrayList<Integer> amounts = new ArrayList<Integer>();
    // Use a pattern and matcher to find values associated with the regex given
    // as a parameter.
    Pattern patternObject = Pattern.compile(regex);
    Matcher matcherObject = patternObject.matcher(allText);
    // If found, add the integer to the collection.
    while (matcherObject.find()) {
      amounts.add(Integer.parseInt(matcherObject.group(1)));
    }
    return amounts;
  }

}
