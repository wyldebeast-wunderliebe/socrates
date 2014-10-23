/*
 * Created on Apr 21, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import com.w20e.socrates.data.IsBoolean;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestIsBoolean extends TestCase {

  public void testEval() {

    IsBoolean isBool = new IsBoolean();
    
    assertTrue(isBool.eval("1"));
    assertTrue(isBool.eval("0"));
    assertTrue(isBool.eval("true"));
    assertTrue(isBool.eval("TrUe"));
    assertTrue(isBool.eval("FaLsE"));
    assertFalse(isBool.eval(""));
    assertFalse(isBool.eval("x"));
  }
}
