package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Matches;
import com.w20e.socrates.expression.XString;

import junit.framework.TestCase;

public class TestMatches extends TestCase {

  
  Matches m = new Matches();

  /*
   * Test method for 'com.w20e.expression.Matches.toString()'
   */
  public void testToString() {

    this.m.setLeftOperand(new XString("aa"));
    this.m.setRightOperand(new XString("bb"));
    
    assertEquals("matches(aa, bb)", this.m.toString());
  }

  /*
   * Test method for 'com.w20e.expression.Matches.toBoolean()'
   */
  public void testToBoolean() {

    this.m.setLeftOperand(new XString("aa"));
    this.m.setRightOperand(new XString("a*"));
    
    assertTrue(this.m.toBoolean());

    this.m.setRightOperand(new XString("b*"));
    
    assertFalse(this.m.toBoolean());
  }

  /*
   * Test method for 'com.w20e.expression.Matches.eval()'
   */
  public void testEval() {
    this.m.setLeftOperand(new XString("aa"));
    this.m.setRightOperand(new XString("a*"));
    
    assertTrue(this.m.eval().toBoolean());

    this.m.setRightOperand(new XString("b*"));
    
    assertFalse(this.m.eval().toBoolean());
  }

}
