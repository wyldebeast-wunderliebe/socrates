/*
 * File      : TestGT.java
 * Classname : TestGT
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestGT.java,v 1.4 2005/01/14 07:56:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.GT;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;

public class TestGT extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private GT op;

  public TestGT(String name) {
    super(name);
  }

  public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new GT();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("GT", this.op.getType());
  }

  public void testToBoolean() {

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(11)));

    assertFalse(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 > 5");
  }

  public void testEval() {

    assertTrue(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertFalse(this.op.eval().toBoolean());
  }

}
