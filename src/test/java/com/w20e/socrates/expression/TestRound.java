/*
 * Created on May 26, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Round;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestRound extends TestCase {

  private Round round;
  
  
  public void setUp() {

    this.round = new Round();
    
    Expression[] args = {new XNumber(new Double(23.994))};
    
    this.round.setOperands(args);
  }

  public void testEval() {

    assertEquals(24, this.round.eval().toNumber().intValue());

    Expression[] args = {new XNumber(new Double(23.094)), new XNumber(new Integer(1))};
    
    this.round.setOperands(args);

    assertEquals(23.1, this.round.eval().toNumber().doubleValue());
  }

  /*
   * Class under test for String toString()
   */
  public void testToString() {

    assertEquals("round(23.994,)", this.round.toString());
  }

  public void testToBoolean() {

    assertTrue(this.round.toBoolean());

    Expression[] args = {new XNumber(new Double(0.194))};

    this.round.setOperands(args);

    assertFalse(this.round.toBoolean());
  }
}
