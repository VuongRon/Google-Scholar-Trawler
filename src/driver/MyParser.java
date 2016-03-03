package driver;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 
 * This class handles the input of args, and the overall output of the file
 * whether it be to a file or the console.
 * 
 * @author Ronald
 *
 */
public class MyParser {

  /**
   * @param args is the arguments given to the main method, composed of html
   *        files, then an optional output filename.
   * @throws Exception when there is a malformed URL or a value is above the
   *         size of an array.
   */
  public static void main(String[] args) throws Exception {
    // This checks if there is an optional output filename given, if so, it will
    // redirect System.out to this file instead of the console. Then it will
    // gather the output, which occurs regardless of there being an outfile.
    if (args.length == 2) {
      PrintStream out = new PrintStream(new FileOutputStream(args[1]));
      // Redirects system.out to the printstream.
      System.setOut(out);
      GatherOutput(args);
      // Closes the file.
      out.close();
    } else {
      GatherOutput(args);
    }
  }


  /**
   * 
   * This method gives the total output outlined in the assignment, where each
   * author gets their own sections first, and the total authors and sorted
   * coauthor list is given at the end.
   * 
   * @param args is the arguments given to the main method, composed of html
   *        files, then an optional output filename.
   * @throws Exception when there is a malformed URL or a value is above the
   *         size of an array.
   */
  private static void GatherOutput(String[] args) throws Exception {
    // This new array holds the html files separately.
    String inputFiles[] = args[0].split(",");
    // The order of these calls matches that of the desired output
    for (String inputFile : inputFiles) {
      helpers.Parser.InitializeCollections(inputFile);
      helpers.Parser.ExtractAuthorsName();
      helpers.Parser.ExtractCitations();
      // The next two methods can take an integer value, and so 3 and 5 are
      // chosen to match the output
      helpers.Parser.ExtractPublications(3);
      helpers.Parser.ExtractPaperCitations(5);
      helpers.Parser.ExtractTotalCoauthors();
    }
    // These commands execute after every html file has been accessed
    helpers.Parser.DashFormatting();
    helpers.Parser.ExtractTotalCoauthorsAll();
    // This method prints every coauthor amongst all authors in alphabetical
    // order.
    helpers.Parser.ExtractAllCoauthorsSorted();
  }

}
