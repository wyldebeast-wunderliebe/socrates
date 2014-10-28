/*
 * File      : TestNot.java
 * Classname : TestNot
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Id: TestNot.java,v 1.7 2006/11/22 15:05:49 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.Not;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XBoolean;

import junit.framework.TestCase;


public class TestNot extends TestCase {

  private XBoolean trueBool;
  private XBoolean falseBool;
  private Not not;

  public TestNot(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.not = new Not();
    this.trueBool = new XBoolean(true);
    this.falseBool = new XBoolean(false);
  }

  public void testGetType() {

    assertEquals("Not", this.not.getType());
  }

  public void testToBoolean() {

    this.not.setLeftOperand(this.trueBool);

    assertFalse(this.not.toBoolean());

    this.not.setLeftOperand(this.falseBool);

    assertTrue(this.not.toBoolean());
    
    this.not.setLeftOperand(new Undef());
    
    assertFalse(this.not.toBoolean());    
  }

  public void testToString() {

    this.not.setLeftOperand(this.trueBool);

    assertEquals(this.not.toString(), "not(true)");

    this.not.setLeftOperand(this.falseBool);

    assertEquals(this.not.toString(), "not(false)");
  }

  public void testEval() {
    
    this.not.setLeftOperand(this.trueBool);

    assertFalse(this.not.eval().toBoolean());

    this.not.setLeftOperand(this.falseBool);

    assertTrue(this.not.eval().toBoolean());
  }
  
  // Unary operation impl test
  public void testSetLeftOperand() {
 
      this.not.setLeftOperand(null);
      assertEquals(Undef.UNDEF.toString(), this.not.getLeftOperand().toString());
  }
}
