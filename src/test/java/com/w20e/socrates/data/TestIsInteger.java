/*
 * Created on Jun 20, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import com.w20e.socrates.data.IsInteger;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestIsInteger extends TestCase {

  public void testEval() {

    IsInteger isInt = new IsInteger();
    
    assertTrue(isInt.eval(Integer.valueOf(10)));

    assertFalse(isInt.eval("pipo"));
  }
}
