/*
 * Created on Jun 23, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.data.Replace;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestReplace extends TestCase {

  public void testTransform() {

    Replace rpl = new Replace("\\.", ",");
    
    assertEquals("3,00", rpl.transform("3.00"));

    assertEquals("3,00", rpl.transform("3.00", new Locale("nl_NL")));
  }

}
