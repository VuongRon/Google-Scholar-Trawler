package helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.AboveIndexException;

/**
 * 
 * This class handles all publications referenced in the output.
 * 
 * @author Ronald
 *
 */
public class Publication {

  private static ArrayList<Publication> publicationCollection =
      new ArrayList<Publication>();
  private String title;

  /**
   * 
   * The constructor for a publication, given a title.
   * 
   * @param title the title of the publication.
   */
  private Publication(String title) {
    this.title = title;
  }

  /**
   * 
   * This method is a factory method which creates the publication and also adds
   * it to the collection.
   * 
   * @param title the title of the publication.
   */
  private static void createPublication(String title) {
    // Add the publication to the collection.
    publicationCollection.add(new Publication(title));
  }

  /**
   * 
   * This method gets the title of the publication object.
   * 
   * @return the title of the publication.
   */
  private String getTitle() {
    return title;
  }

  /**
   * 
   * This method empties the collection of publications.
   * 
   */
  private static void resetArrayLists() {
    // Empties the collection for the next author.
    publicationCollection = new ArrayList<Publication>();
  }

  /**
   * 
   * This method parses the raw html of the url and creates the necessary
   * publications, and adds those to the collection.
   * 
   * @param googleScholarURL the URL in string form to be returned.
   * @throws Exception when the URL does not exist.
   */
  public static void populatePublications(String googleScholarURL)
      throws Exception {
    // Empties the collection for the next author.
    resetArrayLists();
    // The raw html associated with the url.
    String rawHTMLString = helpers.HTML.getHTML(googleScholarURL);
    // The regex to find the titles of the publications.
    String reForTitleExtraction =
        "<a href=\"(.*?)\" class=\"cit-dark-large-link\">(.*?)</a>";
    // Use a pattern and matcher to find the regex.
    Pattern patternObject = Pattern.compile(reForTitleExtraction);
    Matcher matcherObject = patternObject.matcher(rawHTMLString);
    // If found, create the publication.
    while (matcherObject.find()) {
      createPublication(matcherObject.group(2));
    }
  }

  /**
   * 
   * This method retrieves the first X titles in the collection, for X being the
   * parameter amount, and returning these as an array.
   * 
   * @param amount the amount of titles to return.
   * @throws AboveIndexException when amount is greater than the size of the
   *         collection.
   */
  public static Object[] getPublications(Integer amount)
      throws AboveIndexException {
    // Create the collection to be returned.
    ArrayList<String> tempTitleCollection = new ArrayList<String>();
    try {
      // Checks if the parameter amount is larger than the collection.
      if (amount <= publicationCollection.size()) {
        // for the first amount items in the collection, add that many
        // publications to the collection to be returned.
        for (Integer i = 0; i < amount; i++) {
          tempTitleCollection.add(publicationCollection.get(i).getTitle());
        }
      } else {
        throw new AboveIndexException();
      }
    } catch (AboveIndexException e) {
      // If amount is greater than the size of the collection, instead just
      // add all possible publications into the colelction.
      for (Publication publication : publicationCollection) {
        tempTitleCollection.add(publication.getTitle());
      }
    }
    return tempTitleCollection.toArray();

  }

}
