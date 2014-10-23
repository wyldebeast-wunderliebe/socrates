/*
 * Created on Apr 25, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import com.w20e.socrates.data.Pattern;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestPattern extends TestCase {

  public void testEval() {

    Pattern pat = new Pattern("x");
    
    assertTrue(pat.eval("x"));
    assertFalse(pat.eval("y"));
    
    pat = new Pattern(".*");

    assertTrue(pat.eval("x"));
    assertTrue(pat.eval("y"));

    pat = new Pattern("[a-z]{2}");

    assertTrue(pat.eval("xx"));
    assertFalse(pat.eval("xyz"));
  }
}
