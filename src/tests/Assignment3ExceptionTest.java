/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package tests;



import org.junit.Test;

import java.io.FileNotFoundException;


/**
 * 
 * This class tests the expected exception throws for invalid args.
 *
 * @author Ronald
 */
public class Assignment3ExceptionTest {

  /**
   * Test of no valid file giving fileNotFoundException
   * 
   * @throws Exception when the args do not lead to a valid file.
   * 
   */
  @Test(expected = FileNotFoundException.class)
  public void testFileNotFoundException1() throws Exception {
    String[] args = {"xxx"};
    driver.MyParser.main(args);
  }

  /**
   * Test of no valid file giving fileNotFoundException
   * 
   * @throws Exception when the args do not lead to a valid file.
   * 
   */
  @Test(expected = FileNotFoundException.class)
  public void testFileNotFoundException2() throws Exception {
    String args = "xxx";
    helpers.Author.populateAuthors(args);
  }

  /**
   * Test of no valid file giving fileNotFoundException
   * 
   * @throws Exception when the args do not lead to a valid file.
   * 
   */
  @Test(expected = FileNotFoundException.class)
  public void testFileNotFoundException3() throws Exception {
    String args = "xxx";
    helpers.Publication.populatePublications(args);
  }

  /**
   * Test of no valid file giving fileNotFoundException
   * 
   * @throws Exception when the args do not lead to a valid file.
   * 
   */
  @Test(expected = FileNotFoundException.class)
  public void testFileNotFoundException4() throws Exception {
    String args = "xxx";
    helpers.Citation.populateCitations(args);;
  }

}
