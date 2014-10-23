package com.w20e.socrates.expression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCeil {

	private Ceil ceil;
	
	@Before
	public void setUp() throws Exception {
		
		this.ceil = new Ceil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {

		ceil.setLeftOperand(new XNumber(new Float(9.88)));		
		assertEquals("10.0", this.ceil.eval().toString());

		ceil.setLeftOperand(new XNumber(new Float(9.08)));		
		assertEquals("10.0", this.ceil.eval().toString());
	}

	@Test
	public void testToBoolean() {
		ceil.setLeftOperand(new XNumber(new Float(0.1)));
		assertTrue(this.ceil.toBoolean());
		
		ceil.setLeftOperand(new XNumber(new Float(-0.1)));
		assertFalse(this.ceil.toBoolean());
	}

	@Test
	public void testToString() {
		ceil.setLeftOperand(new XNumber(new Float(9.8)));
		assertEquals("ceil(9.8)", this.ceil.toString());
	}
	
}
