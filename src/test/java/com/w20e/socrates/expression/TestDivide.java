/*
 * File      : TestDivide.java
 * Classname : TestDivide
 * Author    : Duco Dokter
 * Date      : 12 Jan 2005
 * Version   : $Id: TestDivide.java,v 1.3 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import junit.framework.TestCase;

public class TestDivide extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private Divide op;

  public TestDivide(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new Divide();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("Divide", this.op.getType());
  }

  public void testToBoolean() {

    assertTrue(this.op.toBoolean());

    this.num0 = new XNumber(Integer.valueOf(0));

    this.op.setLeftOperand(this.num0);

    assertFalse(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 / 5");
  }

  public void testEval() {

    assertTrue(new XNumber( new Double(2) ).equals(this.op.eval()));

    this.op.setRightOperand(new XNumber(Integer.valueOf(100)));
    
    this.op.setLeftOperand(null);
    
    assertEquals(Double.NaN, this.op.eval().toObject());

    this.op.setLeftOperand(new XString("moi"));
    
    assertEquals(Double.NaN, this.op.eval().toObject());
  }

}
