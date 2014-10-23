/*
 * File      : TestLTE.java
 * Classname : TestLTE
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestLTE.java,v 1.4 2006/04/25 06:51:12 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.LTE;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;

public class TestLTE extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private LTE op;

  public TestLTE(String name) {
    super(name);
  }

  public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new LTE();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("LTE", this.op.getType());
  }

  public void testToBoolean() {

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(11)));

    assertTrue(this.op.toBoolean());

    this.op.setLeftOperand(new XNumber(null));

    assertTrue(this.op.toBoolean());
  }

  public void testToString() {
    
    assertEquals(this.op.toString(), "10 <= 5");
  }

  public void testEval() {

    assertFalse(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertTrue(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(11)));

    assertTrue(this.op.eval().toBoolean());
    
    this.op.setLeftOperand(null);

    assertTrue(this.op.eval().toBoolean());
  }
}
