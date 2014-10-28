/*
 * File      : TestModulo.java
 * Classname : TestModulo
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestModulo.java,v 1.3 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Modulo;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;

public class TestModulo extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private Modulo op;

  public TestModulo(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new Modulo();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("Modulo", this.op.getType());
  }

  public void testToBoolean() {

    assertFalse(this.op.toBoolean());

    this.num0 = new XNumber(Integer.valueOf(3));
    this.num1 = new XNumber(Integer.valueOf(2));

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);

    assertTrue(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 % 5");
  }

  public void testEval() {

    assertTrue(new XNumber( new Double(0) ).equals(this.op.eval()));

    this.num0 = new XNumber(Integer.valueOf(3));
    this.num1 = new XNumber(Integer.valueOf(2));

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);

    assertTrue(new XNumber( new Double(1) ).equals(this.op.eval()));
  }
}
