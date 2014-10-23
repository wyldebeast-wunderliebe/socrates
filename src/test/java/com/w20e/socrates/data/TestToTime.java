/*
 * @author Wietze Helmantel
 */
package com.w20e.socrates.data;

import com.w20e.socrates.data.ToTime;
import com.w20e.socrates.expression.XString;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestToTime extends TestCase {

  public void testTransform() {

    ToTime fl = new ToTime();

    assertEquals("00:00", fl.transform(null, null).toString());
    assertEquals("01:00", fl.transform(new XString("01:00"), null).toString());
    assertEquals("12:00", fl.transform(new XString("12"), null).toString());
    assertEquals("2:30", fl.transform(new XString("2:30"), null).toString());
  }

}
