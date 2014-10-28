package com.w20e.socrates.expression;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class TestDateTime extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}

	public void testToBoolean() {

		DateTime dt = new DateTime();
		assertTrue(dt.toBoolean());
	}

	public void testEval() {
	    
        Expression[] objs1 = {new XString("01/06/2007"), new XString("dd/MM/yyyy")};
        Expression[] objs2 = {new XString("pipo"), new XString("yyyy")};

		DateTime dt = new DateTime();
		dt.setOperands(objs1);
		Date date = null;
		
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("01/06/2007");
		} catch (Exception e) {
			fail("Exception in formatter");
		}

		assertEquals(String.valueOf(date.getTime()), dt.eval().toString());
		
		dt = new DateTime();
		dt.setOperands(objs2);
		
		assertNull(dt.eval().toObject());

		dt = new DateTime();
		assertEquals(new Date().getTime(), dt.eval().toNumber().longValue());
	
	}

}
