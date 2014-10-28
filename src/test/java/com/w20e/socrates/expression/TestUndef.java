package com.w20e.socrates.expression;


import junit.framework.TestCase;

public class TestUndef extends TestCase {

  private Undef undef;
  
  @Override
public void setUp() {
    
    this.undef = new Undef();
  }
  
  /*
   * Test method for 'com.w20e.expression.Undef.toString()'
   */
  public void testToString() {

    assertEquals("", this.undef.toString());
  }

  /*
   * Test method for 'com.w20e.expression.Undef.toBoolean()'
   */
  public void testToBoolean() {

    assertFalse(this.undef.toBoolean());
  }

  /*
   * Test method for 'com.w20e.expression.Undef.compareTo(Expression)'
   */
  public void testCompareTo() {

    assertEquals(0, this.undef.compareTo(new Undef()));
    assertEquals(0, this.undef.compareTo(null));
    assertEquals(-1, this.undef.compareTo(new XString("")));
    assertEquals(-1, this.undef.compareTo(new XString("x")));
    assertEquals(-1, this.undef.compareTo(new XNumber(Integer.valueOf(0))));
    assertEquals(-1, this.undef.compareTo(new XNumber(Integer.valueOf(10))));
  }

  /*
   * Test method for 'com.w20e.expression.Undef.equals(Expression)'
   */
  public void testEquals() {
    assertTrue(this.undef.equals(new Undef()));
    assertFalse(this.undef.equals(new XString("")));
    assertFalse(this.undef.equals(new XString("x")));
    assertFalse(this.undef.equals(new XNumber(Integer.valueOf(0))));
    assertFalse(this.undef.equals(new XNumber(Integer.valueOf(10))));
    assertFalse(this.undef.equals(""));
    assertFalse(this.undef.equals(null));
    assertFalse(this.undef.equals("pipo"));
  }

  /*
   * Test method for 'com.w20e.expression.Undef.toNumber()'
   */
  public void testToNumber() {
    assertEquals(Double.NaN, this.undef.toNumber());
  }

  /*
   * Test method for 'com.w20e.expression.Undef.getValue()'
   */
  public void testGetValue() {
    assertNull(this.undef.getValue());
  }

  /*
   * Test method for 'com.w20e.expression.Undef.toObject()'
   */
  public void testToObject() {
    assertNull(this.undef.toObject());
  }

}
