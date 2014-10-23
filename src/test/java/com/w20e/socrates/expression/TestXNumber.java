/*
 * File      : TestXNumber.java
 * Classname : TestXNumber
 * Author    : Duco Dokter
 * Date      :
 * Version   :
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import junit.framework.TestCase;

public class TestXNumber extends TestCase {

	private XNumber num;

	public TestXNumber(String name) {

		super(name);
	}

	public void setUp() {

		this.num = new XNumber(Integer.valueOf(10));
	}

	public void testEquals() {

		assertTrue(this.num.equals(new XNumber(Integer.valueOf(10))));
		assertFalse(this.num.equals(new XNumber(Integer.valueOf(1))));
		
        assertFalse(this.num.equals("pipo"));
        assertFalse(this.num.equals(null));
		
	}

	public void testToBoolean() {

		this.num = new XNumber(Integer.valueOf(0));

		assertFalse(this.num.toBoolean());

		this.num = new XNumber(null);

		assertFalse(this.num.toBoolean());

		this.num = new XNumber(Integer.valueOf(3));

		assertTrue(this.num.toBoolean());

		this.num = new XNumber(Integer.valueOf(-3));

		assertTrue(this.num.toBoolean());

		this.num = new XNumber(Float.valueOf("-0.18"));

		assertTrue(this.num.toBoolean());

		this.num = new XNumber(Float.valueOf("-0.0"));

		assertFalse(this.num.toBoolean());
	}

	public void testToString() {

		assertEquals(this.num.toString(), "10");

		XNumber number = new XNumber(null);
		assertEquals(String.valueOf(Float.NaN), number.toString());
	}

	public void testCompareTo() {

		assertEquals(this.num.compareTo(new XNumber(new Float(10.0))), 0);
		assertEquals(this.num.compareTo(new XString("10")), 0);
		assertEquals(this.num.compareTo(new XNumber(Integer.valueOf(10))), 0);
		assertTrue(this.num.compareTo(new XNumber(Integer.valueOf(40))) < 0);
		assertTrue(this.num.compareTo(new XNumber(Integer.valueOf(-3))) > 0);
		
		this.num = new XNumber(new Float(20.0));
		assertEquals(this.num.compareTo(new XNumber(Integer.valueOf(20))), 0);

        this.num = new XNumber(null);
        assertEquals(0, this.num.compareTo(null));

        assertEquals(0, this.num.compareTo(Undef.UNDEF));
	}

	public void testGetType() {

		assertEquals("XNumber", this.num.getType());
	}

	public void testEval() {

		assertEquals(this.num, this.num.eval());
	}

}
