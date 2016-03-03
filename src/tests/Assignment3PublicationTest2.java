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
 * This class tests all public methods for the Publication class.
 *
 * @author Ronald
 */
public class Assignment3PublicationTest2 {

  @Before
  public void setUp() throws Exception {
    // Sets up output
    helpers.Publication.populatePublications("sample1.html");
  }

  /**
   * 
   * This tests getting the titles of the first amount of papers.
   * 
   * @throws Exception when the amount is greater than the size of the
   *         collection.
   * 
   */
  @Test
  public void testgetPublications() throws Exception {
    Object[] x = helpers.Publication.getPublications(5);
    assertEquals(x[0],
        "Bioclipse: an open source workbench for chemo-and bioinformatics");
    assertEquals(x[1], "The LCB data warehouse");
    assertEquals(x[2],
        "XMPP for cloud computing in bioinformatics supporting discovery and "
            + "invocation of asynchronous web services");
    assertEquals(x[3],
        "Proteochemometric modeling of HIV protease susceptibility");
    assertEquals(x[4],
        "Bioclipse 2: A scriptable integration platform for the life sciences");


  }

}
