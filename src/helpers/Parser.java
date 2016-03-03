// **********************************************************
// Assignment3:
// CDF user_name: c4vuongr
//
// Author: Ronald Vuong
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// *********************************************************
package helpers;

import helpers.Author;

import java.util.ArrayList;

import exceptions.AboveIndexException;


/**
 * 
 * This class handles the parsing of the html files given, and gives the
 * appropriate output described in the assignment.
 * 
 * @author Ronald
 *
 */
public class Parser {

  /**
   * 
   * This method goes through the html files and collects all citations,
   * coauthors and publications.
   * 
   * @param inputFile is the name of the html file being looked at next.
   * @throws Exception when there is a malformed URL or a value is above the
   *         size of an array.
   */
  public static void InitializeCollections(String inputFile) throws Exception {
    // For each class, populate their respective collections then insert dashes
    helpers.Citation.populateCitations(inputFile);
    helpers.Publication.populatePublications(inputFile);
    helpers.Author.populateAuthors(inputFile);
    DashFormatting();
  }

  /**
   * This method inputs dashes accordingly wherever it is called.
   */
  public static void DashFormatting() {
    System.out.println("----------------------------------"
        + "-------------------------------------");
  }


  /**
   * 
   * This method prints the name of the author whom the page belongs to.
   * 
   */
  public static void ExtractAuthorsName() {
    // Print the required header, and fetch the main author name
    try {
      System.out.println("1. Name of Author:");
      System.out.println("        " + helpers.Author.getMainAuthor());
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method prints the number of all citations and the number of i0-indexes
   * after 2009.
   * 
   */
  public static void ExtractCitations() {
    // Print the required headers, and fetch the correct citations.
    try {
      System.out.println("2. Number of All Citations:");
      System.out.println("        " + helpers.Citation.getAllCitations());
      System.out.println("3. Number of i10-index after 2009:");
      System.out.println("        " + helpers.Citation.geti10IndexAfter2009());

    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method prints the total paper citations for the first X papers where X
   * is equal to the amount input.
   * 
   * @param amount the number of citations to return
   * @throws AboveIndexException when the param is above the size of the
   *         collection being referenced.
   */
  public static void ExtractPaperCitations(Integer amount)
      throws AboveIndexException {
    // Print the required header, and fetch the correct amount of citations.
    try {
      System.out.println("5. Total paper citation (first " + amount.toString()
          + " papers):");
      System.out.println("        "
          + helpers.Citation.getPaperCitations(amount));
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method prints the title of the first X papers where X is equal to the
   * amount input.
   * 
   * @param amount the number of publications to return
   * @throws AboveIndexException when the param is above the size of the
   *         collection being referenced.
   */
  public static void ExtractPublications(Integer amount)
      throws AboveIndexException {
    // Print the required header, and fetch the correct amount of publications.
    try {
      System.out.println("4. Title of the first " + amount.toString()
          + " publications:");
      Object[] publications = helpers.Publication.getPublications(amount);
      for (Integer i = 0; i < publications.length; i++) {
        // Since i refers to an index, we add 1 to it to display for the output
        Integer integerForString = i + 1;
        System.out.println("        " + integerForString.toString() + "-   "
            + publications[i].toString());
      }
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method prints the number of all coauthors respective to one author.
   * 
   */
  public static void ExtractTotalCoauthors() {
    // Print the required header, and fetch the total number of coauthors.
    try {
      System.out.println("6. Total Co-Authors:");
      System.out.println("        " + helpers.Author.getTotalCoauthors());
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method prints the number of all coauthors across all authors given in
   * the initial arguments.
   * 
   */
  public static void ExtractTotalCoauthorsAll() {
    // Print the required header, and fetch the sorted list of all coauthors
    // across all authors.
    try {
      System.out.println("7. Co-Author list sorted (Total: "
          + helpers.Author.getTotalCoauthorsAll() + "):");
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method sorts the collection of all coauthors, and prints them in
   * alphabetical order.
   * 
   */
  public static void ExtractAllCoauthorsSorted() {
    // For all authors in the total coauthors collection, sort them
    // alphabetically.
    // Then print them.
    try {
      // Temporary collection to hold the sorted coauthors.
      ArrayList<String> sortedCoauthors = new ArrayList<String>();
      // For all authors in the total coauthors collection, sort them
      // alphabetically.
      for (Object coauthor : helpers.Author.getAllCoauthors()) {
        sortedCoauthors =
            AddAndSortNewName(((Author) coauthor).getName(), sortedCoauthors);
      }
      // After sorting, print each name.
      for (String name : sortedCoauthors) {
        System.out.println(name);
      }
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
  }

  /**
   * 
   * This method takes a name and adds it alphabetically to a collection,
   * returning the collection.
   * 
   * @param name is the name of the coauthor to be added alphabetically to a
   *        temporary collection.
   * @param collection is the collection which holds all of the coauthors in
   *        alphabetical order.
   * @return the collection with all coauthors sorted in alphabetical order.
   */
  private static ArrayList<String> AddAndSortNewName(String name,
      ArrayList<String> collection) {
    // If the sorted collection is empty, just add the name.
    if (collection.size() == 0) {
      collection.add(name);
      return collection;
    }
    // Otherwise, for each index in the sorted collection, we compare this name
    // against the name given as a paramenter. If the parameter name
    // alphabetically precedes the index name, we put the name in the index
    // before it. Otherwise, if we reach the end of the collection, put the name
    // at the end.
    for (Integer i = 0; i < collection.size(); i++) {
      // check if the name to be added is alphabetically prior to the name in
      // the collection.
      if (!(CompareTwoNames(name, collection.get(i)))) {
        // If so, add the name in at that index
        collection.add(i, name);
        return collection;
      } else if (i == collection.size() - 1) {
        // Otherwise, the end of the collection will be reached, in which case
        // add the name at the end of the collection.
        collection.add(name);
        return collection;
      }
    }
    return collection;
  }

  /**
   * 
   * This method comapres two names, returning false if nameOne is
   * alphabetically before nameTwo, otherwise true.
   * 
   * @param nameOne the name of some coauthor.
   * @param nameTwo the name of some other coauthor.
   * @return false if nameOne is alphabetically before nameTwo, otherwise true.
   */
  private static Boolean CompareTwoNames(String nameOne, String nameTwo) {
    // This for loop compares the characters of each name, and if nameOne is
    // alphabetically less than nameTwo, return false, otherwise return true.
    for (Integer i = 0; i < Math.min(nameOne.length(), nameTwo.length()); i++) {
      // checking character at index i
      if (nameOne.charAt(i) < nameTwo.charAt(i)) {
        return false;
      } else if (nameOne.charAt(i) > nameTwo.charAt(i)) {
        return true;
      }
    }
    return false;
  }



}
