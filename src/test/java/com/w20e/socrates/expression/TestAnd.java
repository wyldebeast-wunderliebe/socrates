/*
 * File      : TestAnd.java
 * Classname : TestAnd
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Id: TestAnd.java,v 1.7 2006/11/23 14:36:12 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.And;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;

import junit.framework.TestCase;

public class TestAnd extends TestCase {

  private XBoolean trueBool;
  private XBoolean falseBool;
  private And op;

  public TestAnd(String name) {
    super(name);
  }

  public void setUp() {

    this.op = new And();
    this.trueBool = new XBoolean(true);
    this.falseBool = new XBoolean(false);

    this.op.setLeftOperand(this.trueBool);
    this.op.setRightOperand(this.falseBool);
  }

  public void testGetType() {

    assertEquals("And", this.op.getType());
  }

  public void testToBoolean() {

    assertFalse(this.op.toBoolean());

    this.op.setLeftOperand(this.falseBool);

    assertFalse(this.op.toBoolean());

    this.op.setLeftOperand(this.trueBool);
    this.op.setRightOperand(this.trueBool);

    assertTrue(this.op.toBoolean());

    this.op.setLeftOperand(this.falseBool);

    assertFalse(this.op.toBoolean());

    this.op.setLeftOperand(new XString("x"));
    this.op.setRightOperand(new XString("x"));

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XString(""));

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(1)));

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(0)));

    assertFalse(this.op.toBoolean());

    this.op.setLeftOperand(new Undef());

    assertFalse(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "true and false");
  }

  public void testEval() {
    
    assertFalse(this.op.eval().toBoolean());

    this.op.setRightOperand(new XBoolean(true));

    assertTrue(this.op.eval().toBoolean());
  }
}
