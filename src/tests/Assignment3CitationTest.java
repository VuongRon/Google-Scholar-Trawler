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
public class Assignment3CitationTest {

  @Before
  public void setUp() throws Exception {
    // Sets up output
    helpers.Citation.populateCitations("sample1.html");
  }


  /**
   * 
   * Test getting the total amount of citations for all time.
   * 
   */
  @Test
  public void testgetAllCitations() {
    String x = helpers.Citation.getAllCitations();
    assertEquals(x, "437");

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
    assertTrue(x == 88);
    x = helpers.Citation.getPaperCitations(2);
    assertTrue(x == 88 + 41);
    x = helpers.Citation.getPaperCitations(3);
    assertTrue(x == 88 + 41 + 37);
    x = helpers.Citation.getPaperCitations(4);
    assertTrue(x == 88 + 41 + 37 + 37);
    x = helpers.Citation.getPaperCitations(5);
    assertTrue(x == 88 + 41 + 37 + 37 + 36);
    x = helpers.Citation.getPaperCitations(20);
    assertTrue(x == 409);
    x = helpers.Citation.getPaperCitations(21);
    assertTrue(x == 409);

  }

  /**
   * 
   * Test getting the i10-index for 2009.
   * 
   */
  @Test
  public void testgeti10Index() {
    String x = helpers.Citation.geti10IndexAfter2009();
    assertEquals(x, "12");

  }

}
