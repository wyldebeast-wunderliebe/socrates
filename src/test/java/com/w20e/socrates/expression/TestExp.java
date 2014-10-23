/*
 * Created on May 23, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Exp;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestExp extends TestCase {

  Exp exp = new Exp();
  
  public void testEval() {

    this.exp.setLeftOperand(new XNumber(Integer.valueOf(0)));
    assertEquals("1.0", this.exp.eval().toString());

    this.exp.setLeftOperand(new XNumber(Integer.valueOf(4)));
    assertEquals(new Double(Math.exp(4)),
        new Double(this.exp.eval().toNumber().doubleValue()));
  }

  /*
   * Class under test for String toString()
   */
  public void testToString() {

    this.exp.setLeftOperand(new XNumber(Integer.valueOf(5)));

    assertEquals("exp(5)", this.exp.toString());
  }

  /*
   * Class under test for String toString()
   */
  public void testToBoolean() {

    this.exp.setLeftOperand(new XNumber(Integer.valueOf(5)));

    assertTrue(this.exp.toBoolean());
  }

}
