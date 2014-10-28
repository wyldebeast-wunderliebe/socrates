package com.w20e.socrates.rendering;

import java.util.HashMap;

import junit.framework.TestCase;

public class TestTime extends TestCase {

	private Time control;
	
	@Override
	public void setUp() {
		this.control = new Time("time0");
	}
	
	public void testProcessInput() {

		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("time0", "10:30");
		
		Object n0 = this.control.processInput(data);

		data.put("time0", "10:30");
		
		Object n1 = this.control.processInput(data);

		assertTrue(n0.equals(n1));

		data.put("time0", "11:30");
		
		n1 = this.control.processInput(data);
		
		assertFalse(n0.equals(n1));

		assertTrue(((java.util.Date) n0).compareTo((java.util.Date) n1) < 0);

		data.put("time0", "00:30");
		
		n1 = this.control.processInput(data);

		assertTrue(((java.util.Date) n0).compareTo((java.util.Date) n1) > 0);
		
		data.put("time0", "xx");
		
		assertNull(this.control.processInput(data));
	}

	public void testSetFormat() {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("time0", "10:10");

		Object n0 = this.control.processInput(data);		

		data.put("time0", "10.10");		
		this.control.setProperty("format", "hh.mm");
		
		Object n1 = this.control.processInput(data);		
		
		assertEquals(n0, n1);
	}

	public void testGetDisplayValue() {
		
		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("time0", "10:30");

		Object n0 = this.control.processInput(data);		

		Object displayVal = this.control.getDisplayValue(n0, null, null);
		
		assertEquals("10:30", displayVal);
	}
}
