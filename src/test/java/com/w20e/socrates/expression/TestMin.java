/*
 * Created on May 30, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Min;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestMin extends TestCase {

  private Min min;

  /*
   * @see TestCase#setUp()
   */
  protected void setUp() throws Exception {

    super.setUp();
    this.min = new Min();
  }

  public void testEval() {

    this.min.setLeftOperand(new XNumber(Integer.valueOf(0)));
    this.min.setRightOperand(new XNumber(Integer.valueOf(1)));
    
    assertEquals(0, this.min.eval().toNumber().intValue());

    this.min.setRightOperand(new XNumber(Integer.valueOf(0)));
    assertEquals(0, this.min.eval().toNumber().intValue());

    this.min.setRightOperand(new XNumber(Integer.valueOf(-1)));

    assertEquals(-1, this.min.eval().toNumber().intValue());
  }

  /*
   * Class under test for String toString()
   */
  public void testToString() {

    this.min.setLeftOperand(new XNumber(Integer.valueOf(0)));
    this.min.setRightOperand(new XNumber(Integer.valueOf(1)));

    assertEquals("min(0, 1)", this.min.toString());
  }

  public void testToBoolean() {

    this.min.setLeftOperand(new XNumber(Integer.valueOf(0)));
    this.min.setRightOperand(new XNumber(Integer.valueOf(1)));

    assertFalse(this.min.toBoolean());

    this.min.setLeftOperand(new XNumber(Integer.valueOf(3)));
    
    assertTrue(this.min.toBoolean());
  }

}
