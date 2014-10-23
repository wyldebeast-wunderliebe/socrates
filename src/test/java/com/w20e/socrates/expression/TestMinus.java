/*
 * File      : TestMinus.java
 * Classname : TestMinus
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestMinus.java,v 1.3 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Minus;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;

public class TestMinus extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private Minus op;

  public TestMinus(String name) {
    super(name);
  }

  public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new Minus();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("Minus", this.op.getType());
  }

  public void testToBoolean() {

    assertTrue(this.op.toBoolean());

    this.num0 = new XNumber(Integer.valueOf(0));
    this.num1 = new XNumber(Integer.valueOf(0));

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);

    assertFalse(this.op.toBoolean());

    this.num0 = new XNumber(Integer.valueOf(-1));
    this.num1 = new XNumber(Integer.valueOf(-1));

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);

    assertFalse(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 - 5");
  }

  public void testEval() {
    
    assertTrue(new XNumber( new Double(5) ).equals(this.op.eval()));
  }
}
