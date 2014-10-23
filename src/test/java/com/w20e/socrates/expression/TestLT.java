/*
 * File      : TestLT.java
 * Classname : TestLT
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestLT.java,v 1.3 2005/06/15 13:05:26 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.LT;
import com.w20e.socrates.expression.XNumber;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestLT extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private LT op;

  public TestLT(String name) {
    super(name);
  }

  public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new LT();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("LT", this.op.getType());
  }

  public void testToBoolean() {

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(11)));

    assertTrue(this.op.toBoolean());
  }

  public void testToString() {
    
    assertEquals(this.op.toString(), "10 < 5");
  }

  public void testEval() {

    assertFalse(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertFalse(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(11)));

    assertTrue(this.op.eval().toBoolean());
    
    this.op.setLeftOperand(null);

    assertTrue(this.op.eval().toBoolean());
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new TestLT("testToBoolean"));
    suite.addTest(new TestLT("testGetType"));
    suite.addTest(new TestLT("testToString"));
    suite.addTest(new TestLT("testEval"));
    return suite;
  }
}
