/*******************************************************************************
 *
 * File      : TestMultiply.java
 * Author    : Duco Dokter
 * Created   : Mon Jul 26 11:38:54 2004
 * Version   : $Id: TestMultiply.java,v 1.3 2005/01/15 11:04:58 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 *
 ******************************************************************************/

package com.w20e.socrates.expression;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.w20e.socrates.expression.Multiply;
import com.w20e.socrates.expression.XNumber;

public class TestMultiply extends TestCase {

  private XNumber num0;

  private XNumber num1;

  private Multiply op;

  public TestMultiply(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.num0 = new XNumber(Integer.valueOf(10));
    this.num1 = new XNumber(Integer.valueOf(5));
    this.op = new Multiply();

    this.op.setLeftOperand(this.num0);
    this.op.setRightOperand(this.num1);
  }

  public void testGetType() {

    assertEquals("Multiply", this.op.getType());
  }

  public void testToBoolean() {

    assertTrue(this.op.toBoolean());
  }

  public void testToString() {

    assertEquals(this.op.toString(), "10 * 5");
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new TestMultiply("testToBoolean"));
    suite.addTest(new TestMultiply("testToString"));
    suite.addTest(new TestMultiply("testGetType"));
    return suite;
  }
}
