package com.w20e.socrates.rendering;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.w20e.socrates.data.XSString;

public class TestCheckbox extends TestCase {

	Checkbox control;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		this.control = new Checkbox("ctrl0");
	}

	public void testProcessInput() {

		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("ctrl0", "");
		
		assertNull(this.control.processInput(data));

		data.put("ctrl0", "false");
		
		assertEquals("false", this.control.processInput(data));

		data.put("ctrl0", "true");
		
		assertEquals("true", this.control.processInput(data));
	}

	public void testGetDisplayValue() {

	    assertEquals("false", this.control.getDisplayValue(null, null, null));
        assertEquals("false", this.control.getDisplayValue("", null, null));
        assertEquals("true", this.control.getDisplayValue("pipo", XSString.class, null));

	}
}
