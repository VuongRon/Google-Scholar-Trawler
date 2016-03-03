/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package tests;



import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * 
 * This class tests all public methods for the Author class.
 *
 * @author Ronald
 */
public class Assignment3AuthorTest {

  @Before
  public void setUp() throws Exception {
    // Sets up output
    helpers.Author.populateAuthors("sample1.html");
  }


  /**
   * 
   * Tests getting the main author
   * 
   */
  @Test
  public void testgetMainAuthor() {
    String x = helpers.Author.getMainAuthor();
    assertEquals(x, "Ola Spjuth");

  }

  /**
   * 
   * Tests getting the total amount of coauthors for the single author
   * 
   */
  @Test
  public void testgetTotalCoauthors() {
    Integer x = helpers.Author.getTotalCoauthors();
    assertTrue(x == 15);

  }

  /**
   * 
   * Tests getting the total amount of coauthors across all authors
   * 
   */
  @Test
  public void testgetTotalCoauthorsAll() {
    Object[] y = helpers.Author.getAllCoauthors();
    Integer x = helpers.Author.getTotalCoauthorsAll();
    assertTrue(x == y.length);

  }

}
