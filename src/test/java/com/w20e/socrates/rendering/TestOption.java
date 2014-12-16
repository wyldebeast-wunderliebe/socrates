/*
 * Created on Mar 12, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.rendering;


import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestOption extends TestCase {

  /*
   * @see TestCase#setUp()
   */
  @Override
protected void setUp() throws Exception {

    super.setUp();
  }

  /*
   * Class under test for void Option(String, String)
   */
  public void testOptionStringString() {

    Option opt = new Option("opt_0", new Label("Option 0"));

    assertEquals(opt.getValue(), "opt_0");
    assertEquals(opt.getLabel().toString(), "Option 0");
  }

  /*
   * Class under test for void Option()
   */
  public void testOption() {

    Option opt = new Option();

    assertNull(opt.getValue());
    assertEquals("", opt.getLabel().toString());
  }

  public void testSetName() {

    Option opt = new Option();
    opt.setValue("opt0");
    assertEquals(opt.getValue(), "opt0");
  }

  public void testSetLabel() {

    Option opt = new Option();
    opt.setLabel(new Label("Option 0"));
    assertEquals("Option 0", opt.getLabel().toString());
  }
  
  public void testGetHint() {
      Option opt = new Option();
      assertEquals("", opt.getHint());
      opt.setHint("HINT");
      assertEquals("HINT", opt.getHint());
  }

}
