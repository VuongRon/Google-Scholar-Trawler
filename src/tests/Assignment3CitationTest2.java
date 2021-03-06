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
 * This class tests all public methods for the Citation class.
 *
 * @author Ronald
 */
public class Assignment3CitationTest2 {

  @Before
  public void setUp() throws Exception {
    // Sets up output
    helpers.Citation.populateCitations("sample2.html");
  }

  /**
   * 
   * Test getting the total amount of citations for all time.
   * 
   */
  @Test
  public void testgetAllCitations() {
    String x = helpers.Citation.getAllCitations();
    assertEquals(x, "263");

  }

  /**
   * 
   * Test getting the amount of paper citrations for different amounts of papers
   * observed.
   * 
   * @throws Exception when the amount is higher than the size of the
   *         collection.
   */
  @Test
  public void testgetPaperCitations() throws Exception {
    Integer x = helpers.Citation.getPaperCitations(1);
    assertTrue(x == 51);
    x = helpers.Citation.getPaperCitations(2);
    assertTrue(x == 51 + 35);
    x = helpers.Citation.getPaperCitations(3);
    assertTrue(x == 51 + 35 + 26);
    x = helpers.Citation.getPaperCitations(4);
    assertTrue(x == 51 + 35 + 26 + 25);
    x = helpers.Citation.getPaperCitations(5);
    assertTrue(x == 51 + 35 + 26 + 25 + 21);
    x = helpers.Citation.getPaperCitations(20);
    assertTrue(x == 262);
    x = helpers.Citation.getPaperCitations(21);
    assertTrue(x == 262);

  }

  /**
   * 
   * Test getting the i10-index for 2009.
   * 
   */
  @Test
  public void testgeti10Index() {
    String x = helpers.Citation.geti10IndexAfter2009();
    assertEquals(x, "9");

  }

}
