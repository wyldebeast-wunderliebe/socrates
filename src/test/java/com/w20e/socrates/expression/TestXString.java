/*
 * File      : TestXString.java
 * Classname : TestXString
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: TestXString.java,v 1.9 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 *
 */

package com.w20e.socrates.expression;

import junit.framework.TestCase;

import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;

public class TestXString extends TestCase {

  private XString str;

  public TestXString(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.str = new XString("foo");
  }

  public void testEquals() {

    assertTrue(this.str.equals(this.str.eval()));
    
    XNumber num = new XNumber(Integer.valueOf(1));
    
    this.str = new XString("1");

    assertTrue(this.str.equals(num));

    assertFalse(this.str.equals(null));

    assertFalse(this.str.equals("pipo"));
  }

  public void testGetType() {

    assertEquals("XString", this.str.getType());
  }

  public void testToBoolean() {

    assertTrue(this.str.toBoolean());

    this.str = new XString(null);

    assertFalse(this.str.toBoolean());

    this.str = new XString("");

    assertFalse(this.str.toBoolean());
  }

  public void testToString() {

    assertEquals(this.str.toString(), "foo");
  }

  public void testCompareTo() {

    assertTrue(this.str.compareTo(new XString("foo")) == 0);
    assertTrue(this.str.compareTo(new XString("goo")) < 0);
    assertTrue(this.str.compareTo(new XString("doo")) > 0);

    this.str = new XString(null);
    
    assertTrue(this.str.compareTo(null) == 0);

    this.str = new XString("");
    
    assertTrue(this.str.compareTo(null) == 1);

    this.str = new XString(null);
    
    assertTrue(this.str.compareTo(new XString("")) == -1);

  }

  public void testToNumber() {

    assertEquals(Float.valueOf(Float.NaN), this.str.toNumber());

    this.str = new XString("23");
    assertEquals(this.str.toNumber(), new Double(23));

    this.str = new XString(" 23 ");
    assertEquals(this.str.toNumber(), new Double(23));

    this.str = new XString("-23");
    assertEquals(this.str.toNumber(), new Double(-23));

    this.str = new XString(" - 23 ");
    assertEquals(this.str.toNumber(), new Double(-23));
  }

  public void testEval() {

    assertEquals(this.str, this.str.eval());
  }
 
  public void testHashCode() {
      
      this.str = new XString("pipo");
      
      assertEquals("pipo".hashCode(), this.str.hashCode());
  }
}
