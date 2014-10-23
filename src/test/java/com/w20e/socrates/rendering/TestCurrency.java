package com.w20e.socrates.rendering;

import java.util.HashMap;
import java.util.Locale;

import junit.framework.TestCase;

import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;

public class TestCurrency extends TestCase {

	private Currency curr;

	protected void setUp() throws Exception {
		super.setUp();
		this.curr = new Currency("curr");
	}

	public void testGetDisplayValue() {

		XNumber num = new XNumber(new Float(3.5));

		Locale l = new Locale("en", "US");
		
		assertEquals("$3.50", this.curr.getDisplayValue(num.toObject(), null, l));
        assertEquals("", this.curr.getDisplayValue(new XString("xxx"), null, null));
	}
	
	public void testProcessInput() {
	    
	    HashMap<String, Object> data = new HashMap<String, Object>();
	    
	    data.put("curr", "10,30");
	    
	    assertEquals("10,30", this.curr.processInput(data));

	    
	    data.put("curr", "");
	        
	    assertNull(this.curr.processInput(data));

        data.put("currleft", "455");
        data.put("currright", "05");
        
        assertEquals("455.05", this.curr.processInput(data));
        
        data.clear();
        
	    assertNull(this.curr.processInput(data));
	
	}

	public void testGetType() {
		assertEquals("currency", this.curr.getType());
	}
}
