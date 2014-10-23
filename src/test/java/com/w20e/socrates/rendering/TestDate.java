package com.w20e.socrates.rendering;

import java.util.HashMap;

import junit.framework.TestCase;

public class TestDate extends TestCase {

	private Date control;
	
	public void setUp() {
		this.control = new Date("date0");
	}
	
	public void testProcessInput() {

		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("date0", "10/01/2000");
		
		java.util.Date n0 = (java.util.Date) this.control.processInput(data);

		data.put("date0", "10/01/2000");
		
		java.util.Date n1 = (java.util.Date) this.control.processInput(data);

		assertTrue(n0.equals(n1));

		data.put("date0", "11/01/2000");
		
		n1 = (java.util.Date) this.control.processInput(data);

		assertFalse(n0.equals(n1));

		assertTrue(n0.compareTo(n1) < 0);

		data.put("date0", "09/01/2000");
		
		n1 = (java.util.Date) this.control.processInput(data);

		assertTrue(n0.compareTo(n1) > 0);

		data.put("date0", "10/02/2000");
		
		n1 = (java.util.Date) this.control.processInput(data);

		assertTrue(n0.compareTo(n1) < 0);

		data.put("date0", "10/02/1950");
		
		n1 = (java.util.Date) this.control.processInput(data);

		assertTrue(n0.compareTo(n1) > 0);

		data.put("date0", "10-02-1950");
	        
		assertNull(this.control.processInput(data));
		
		data.clear();
		
		try {
			this.control.processInput(data);
			fail();
		} catch (Exception e) {
			// ok
		}
	}

	public void testSetFormat() {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("date0", "10/01/2000");

		java.util.Date n0 = (java.util.Date) this.control.processInput(data);		

		data.put("date0", "01/10/2000");		
		this.control.setProperty("format", "MM/dd/yyyy");
		
		java.util.Date n1 = (java.util.Date) this.control.processInput(data);		
		
		assertEquals(n0, n1);
	}

	public void testGetDisplayValue() {
		
		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("date0", "10/01/2000");

		java.util.Date n0 = (java.util.Date) this.control.processInput(data);		

		Object displayVal = this.control.getDisplayValue(n0, null, null);
		
		assertEquals("10/01/2000", displayVal);
		
		assertEquals("", this.control.getDisplayValue("XXX", null, null));
	}
}
