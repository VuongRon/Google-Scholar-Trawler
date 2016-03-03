package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class tests all public methods for the Parser and Output class.
 *
 * @author Ronald
 */
public class Assignment3OutputAndParserTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final String[] args = {"sample2.html,sample1.html"};

  /**
   * 
   * This changes system output to go to this printstream instead, which we will
   * use later.
   * 
   */
  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
  }

  /**
   * 
   * This tests that the entire output is correct, starting from the main
   * function.
   * 
   * @throws Exception when the amount for the relevant methods are greater than
   *         their respective collections.
   */
  @Test
  public void testOutput() throws Exception {
    driver.MyParser.main(args);
    String output = outContent.toString();
    output = output.replaceAll("\r\n", "");
    assertEquals("------------------------------------------------------------"
        + "-----------1. Name of Author:        Yan Xu2. Number of All "
        + "Citations:        2633. Number of i10-index after 2009:        "
        + "94. Title of the first 3 publications:        1-   Face-tracking"
        + " " + "as an augmented input in video games: enhancing presence, "
        + "role-playing and control        2-   Art of defense: a collabor"
        + "ative" + " handheld augmented reality board game        3-   "
        + "Sociable killers: understanding social relationships in an onli"
        + "ne "
        + "first-person shooter game5. Total paper citation (first 5 paper"
        + "s):"
        + "        1586. Total Co-Authors:        14----------------------"
        + "----"
        + "---------------------------------------------1. Name of Author"
        + ":     "
        + "   Ola Spjuth2. Number of All Citations:        4373. Number o"
        + "f i10-"
        + "index after 2009:        124. Title of the first 3 publication"
        + "s:    "
        + "    1-   Bioclipse: an open source workbench for chemo-and bio"
        + "inform"
        + "atics        2-   The LCB data warehouse        3-   XMPP for "
        + "cloud "
        + "computing in bioinformatics supporting discovery and invocatio"
        + "n of a"
        + "synchronous web services5. Total paper citation (first 5 paper"
        + "s):   "
        + "     2396. Total Co-Authors:        15------------------------"
        + "------"
        + "-----------------------------------------7. Co-Author list sor"
        + "ted (T"
        + "otal: 29):Abigail SellenAdam AmeurAndrew D MillerAntony John W"
        + "illiam"
        + "sBlair MacIntyreChristoph SteinbeckDeepak JagdishE.D. MynattEg"
        + "on Wil"
        + "lighagenElsa EiriksdottirErika Shehan PooleGreg TurkIulian Rad"
        + "uJanna"
        + " HastingsJohn StaskoJonathan AlvarssonKomorowski JanKurt Luthe"
        + "rNina "
        + "JeliazkovaNoel M. O'BoyleRajarshi GuhaSam AdamsSamuel LampaSea"
        + "n Eki"
        + "nsThore GraepelValentin GeorgievXiang CaoYoun-ah Kanggilleain"
        + " torran" + "ce", output);
  }

}
