package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

public class HTML {

  /**
   * 
   * This method takes a URL as a string, and returns the html for that url as a
   * string.
   * 
   * @param urlString the URL in string form to be returned.
   * @return the html for the URL given.
   * @throws Exception when the URL does not exist
   */
  public static String getHTML(String urlString) throws Exception {
    // create object to store html source text as it is being collected
    StringBuilder html = new StringBuilder();
    // open connection to given url
    URL url = new File(urlString).toURI().toURL();
    // create BufferedReader to buffer the given url's HTML source
    BufferedReader htmlbr =
        new BufferedReader(new InputStreamReader(url.openStream()));
    String line;
    // read each line of HTML code and store in StringBuilder
    while ((line = htmlbr.readLine()) != null) {
      html.append(line);
    }
    htmlbr.close();
    // convert StringBuilder into a String and return it
    return html.toString();
  }

}
