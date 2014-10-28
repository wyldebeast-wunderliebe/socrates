/*
 * File      : TestOr.java
 * Classname : TestOr
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestOr.java,v 1.6 2006/11/23 14:36:12 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Or;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;

import junit.framework.TestCase;

public class TestOr extends TestCase {

  private XBoolean trueBool;
  private XBoolean falseBool;
  private Or op;

  public TestOr(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.op = new Or();
    this.trueBool = new XBoolean(true);
    this.falseBool = new XBoolean(false);

    this.op.setLeftOperand(this.trueBool);
    this.op.setRightOperand(this.falseBool);
  }

  public void testGetType() {

    assertEquals("Or", this.op.getType());
  }

  public void testToBoolean() {

    assertTrue(this.op.toBoolean());

    this.op.setLeftOperand(this.falseBool);

    assertFalse(this.op.toBoolean());

    this.op.setLeftOperand(this.trueBool);
    this.op.setRightOperand(this.trueBool);

    assertTrue(this.op.toBoolean());

    this.op.setLeftOperand(this.falseBool);

    assertTrue(this.op.toBoolean());

    this.op.setLeftOperand(new XString(""));
    this.op.setRightOperand(new XString("x"));

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XString(""));

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(1)));

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(0)));

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new Undef());

    assertFalse(this.op.toBoolean());
    
    this.op.setLeftOperand(new XString("x"));

    assertTrue(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "true or false");
  }

  public void testEval() {
    
    assertTrue(this.op.eval().toBoolean());

    this.op.setLeftOperand(new XBoolean(false));

    assertFalse(this.op.eval().toBoolean());
  }
}
