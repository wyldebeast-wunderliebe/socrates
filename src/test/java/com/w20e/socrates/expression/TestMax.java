/*
 * Created on May 30, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Max;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestMax extends TestCase {

  private Max max;

  /*
   * @see TestCase#setUp()
   */
  @Override
protected void setUp() throws Exception {

    super.setUp();
    this.max = new Max();
}

  public void testEval() {

    this.max.setLeftOperand(new XNumber(Integer.valueOf(0)));
    this.max.setRightOperand(new XNumber(Integer.valueOf(1)));
    
    assertEquals(1, this.max.eval().toNumber().intValue());

    this.max.setRightOperand(new XNumber(Integer.valueOf(0)));
    assertEquals(0, this.max.eval().toNumber().intValue());

    this.max.setRightOperand(new XNumber(Integer.valueOf(-1)));

    assertEquals(0, this.max.eval().toNumber().intValue());
  }

  /*
   * Class under test for String toString()
   */
  public void testToString() {

    this.max.setLeftOperand(new XNumber(Integer.valueOf(0)));
    this.max.setRightOperand(new XNumber(Integer.valueOf(1)));

    assertEquals("max(0, 1)", this.max.toString());
  }

  public void testToBoolean() {

    this.max.setLeftOperand(new XNumber(Integer.valueOf(0)));
    this.max.setRightOperand(new XNumber(Integer.valueOf(-1)));

    assertFalse(this.max.toBoolean());

    this.max.setLeftOperand(new XNumber(Integer.valueOf(3)));
    
    assertTrue(this.max.toBoolean());
  }

}
