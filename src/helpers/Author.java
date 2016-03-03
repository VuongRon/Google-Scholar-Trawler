package helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * This class handles all authors referenced in the output, whether it be a
 * coauthor or the main author.
 * 
 * @author Ronald
 *
 */
public class Author {

  private ArrayList<Author> coauthorCollection = new ArrayList<Author>();
  private static ArrayList<Author> coauthorCollectionTotal =
      new ArrayList<Author>();
  private static Author mainAuthor;
  private String name;

  /**
   * 
   * The constructor which sets the name of the author and initializes a
   * collection of coauthors.
   * 
   * @param name the name of the author.
   * @param coauthorCollection the collection of coauthors associated with the
   *        author.
   */
  private Author(String name, ArrayList<Author> coauthorCollection) {
    // This sets the name and collection of coauthors for an author.
    this.name = name;
    this.coauthorCollection = coauthorCollection;
  }

  /**
   * 
   * The constructor which creates a coauthor, and puts this author in the
   * collection of the main author's coauthors.
   * 
   * @param name the name of the coauthor.
   * @param mainAuthor the main author to which this coauthor belongs to.
   */
  private Author(String name, Author mainAuthor) {
    this.name = name;
    // This constructs a coauthor, and so we must add this author to the main
    // author's coauthor collection.
    mainAuthor.coauthorCollection.add(this);
    // There are no coauthors for a coauthor.
    this.coauthorCollection = null;
  }

  /**
   * 
   * A factory method to create a coauthor and add this object to the coauthor
   * collections.
   * 
   * @param name the name of the author.
   */
  private static void createCoauthor(String name) {
    // To construct a coauthor, we must give the main author as an argument.
    Author temp = new Author(name, mainAuthor);
    // We add this author to the total collection of all coauthors across all
    // input files.
    coauthorCollectionTotal.add(temp);
  }

  /**
   * 
   * A factory method to create the main author and sets this object as the main
   * author.
   * 
   * @param name the name of the author.
   */
  private static void createMainAuthor(String name) {
    ArrayList<Author> temp = new ArrayList<Author>();
    // We initially declare a main author with an empty collection of coauthors.
    mainAuthor = new Author(name, temp);
  }

  /**
   * 
   * This method gets the name of the author object.
   * 
   * @return the name of the author.
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * This method finds the main author in the html of the url.
   * 
   * @param googleScholarURL the URL in string form to be returned.
   * @throws Exception when the URL does not exist.
   */
  private static void populateMainAuthor(String googleScholarURL)
      throws Exception {
    // Fetch the raw html
    String rawHTMLString = helpers.HTML.getHTML(googleScholarURL);
    // The regex to look for the main author line
    String reForAuthorExtraction =
        "<span id=\"cit-name-display\" "
            + "class=\"cit-in-place-nohover\">(.*?)</span>";
    // Use pattern and matcher to find the regex in the html
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(rawHTMLString);
    while (matcherObject.find()) {
      // When the main author is found, create the main author
      createMainAuthor(matcherObject.group(1));
    }
  }

  /**
   * 
   * This method finds all coauthors in the html of the url, and puts them in a
   * collection.
   * 
   * @param googleScholarURL the URL in string form to be returned.
   * @throws Exception when the URL does not exist.
   */
  public static void populateAuthors(String googleScholarURL) throws Exception {
    populateMainAuthor(googleScholarURL);
    // Fetch the raw html
    String rawHTMLString = helpers.HTML.getHTML(googleScholarURL);
    // The regex to look for the coauthors
    String reForTitleExtraction =
        "<a class=\"cit-dark-link\" href=\"(.*?)\" title=\"(.*?)\">(.*?)</a>";
    // Use pattern and matcher to find the regex in the html
    Pattern patternObject = Pattern.compile(reForTitleExtraction);
    Matcher matcherObject = patternObject.matcher(rawHTMLString);
    while (matcherObject.find()) {
      // Setup a string variable to convert html for symbols into those symbols
      String htmlConversion = matcherObject.group(2);
      htmlConversion = htmlConversion.replaceAll("&#39;", "'");
      htmlConversion = htmlConversion.replaceAll("&#8208;", "-");
      // After conversion, if the string at group 2 matches the string at group
      // 3, then create that coauthor.
      if (htmlConversion.equals(matcherObject.group(3))) {
        createCoauthor(matcherObject.group(3));
      }
    }
  }

  /**
   * 
   * This method gets the name of the main author.
   * 
   * @return the name of the main author
   */
  public static String getMainAuthor() {
    return mainAuthor.name;
  }

  /**
   * 
   * This method gets the number of coauthors for any given author.
   * 
   * @return the size of the coauthor collection.
   */
  public static Integer getTotalCoauthors() {
    return mainAuthor.coauthorCollection.size();
  }

  /**
   * 
   * This method gets the number of coauthors across all authors.
   * 
   * @return the size of the total coauthor collection for all coauthors across
   *         every author.
   */
  public static Integer getTotalCoauthorsAll() {
    return coauthorCollectionTotal.size();
  }

  /**
   * 
   * This method gets the array of all coauthors across all authors.
   * 
   * @return the array of all coauthors across all authors.
   */
  public static Object[] getAllCoauthors() {
    return coauthorCollectionTotal.toArray();
  }

}
