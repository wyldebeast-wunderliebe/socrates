/*
 * Created on Jun 23, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.data.Format;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;


/**
 */
public class TestFormat extends TestCase {

  public void testTransform() {

    Format fmt = new Format("%.2f");

    assertEquals("3.14", fmt.transform(new XNumber(new Float(3.1415)), Locale.US).toString());
    assertEquals("3,14", fmt.transform(new XNumber(new Float(3.1415)), Locale.FRANCE).toString());    

    assertEquals("3,14", fmt.transform(new Float(3.1415), Locale.FRANCE).toString());
    
    fmt = new Format("%s");

    assertEquals("foo", fmt.transform("foo"));
  }
}
