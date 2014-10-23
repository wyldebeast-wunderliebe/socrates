package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestIsDouble {

	@Test
	public void testEval() {

		Double d = new Double(10);
		Integer i = new Integer(10);
		
		IsDouble isDouble = new IsDouble();
		
		assertTrue(isDouble.eval(d));
		assertFalse(isDouble.eval(i));
		assertFalse(isDouble.eval(""));
		assertFalse(isDouble.eval(null));
	}

}
