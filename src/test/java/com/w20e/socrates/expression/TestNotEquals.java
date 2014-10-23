/*
 * File      : TestNotEquals.java
 * Classname : TestNotEquals
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestNotEquals.java,v 1.5 2005/08/29 12:25:00 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.NotEquals;
import com.w20e.socrates.expression.XNumber;

import junit.framework.TestCase;

public class TestNotEquals extends TestCase {

  private XNumber num0;
  private XNumber num1;
  private NotEquals op;

  public TestNotEquals(String name) {
    super(name);
  }

  public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new NotEquals();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("NotEquals", this.op.getType());
  }

  public void testToBoolean() {

    assertTrue(this.op.toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertFalse(this.op.toBoolean());    
  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 != 5");
  }

  public void testEval() {
    
    assertTrue(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(10)));

    assertFalse(this.op.eval().toBoolean());

    this.op.setLeftOperand(null);

    assertTrue(this.op.eval().toBoolean());

    this.op.setRightOperand(new XNumber(Integer.valueOf(0)));

    assertTrue(this.op.eval().toBoolean());  

    this.op.setRightOperand(new XString("foo"));

    assertTrue(this.op.eval().toBoolean());  
    
    this.op.setLeftOperand(this.num0);

    assertTrue(this.op.eval().toBoolean());
  }
}
