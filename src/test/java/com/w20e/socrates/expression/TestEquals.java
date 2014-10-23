/*
 * File      : TestEquals.java
 * Classname : TestEquals
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestEquals.java,v 1.5 2006/07/07 08:40:42 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Equals;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;

public class TestEquals extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private Equals op;

  public TestEquals(String name) {
    super(name);
  }

  public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new Equals();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("Equals", this.op.getType());
  }

  public void testToBoolean() {

    assertFalse(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertTrue(this.op.toBoolean());    

    this.op.setLeftOperand(new Undef());
    
    assertFalse(this.op.toBoolean());
    
    this.op.setLeftOperand(null);

    this.op.setRightOperand(null);

    assertTrue(this.op.toBoolean());

  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 == 5");
  }

  public void testEval() {
    
    assertFalse(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertTrue(this.op.eval().toBoolean());

    this.op.setLeftOperand(null);

    assertFalse(this.op.eval().toBoolean());
    
    this.op.setRightOperand(new XNumber(Integer.valueOf(0)));

    assertFalse(this.op.eval().toBoolean());

    this.op.setRightOperand(new XString("foo"));

    assertFalse(this.op.eval().toBoolean());
    
  }
}
