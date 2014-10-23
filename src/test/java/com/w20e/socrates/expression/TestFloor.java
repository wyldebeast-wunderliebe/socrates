package com.w20e.socrates.expression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFloor {

	private Floor floor;
	
	@Before
	public void setUp() throws Exception {
		
		this.floor = new Floor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {

		this.floor.setLeftOperand(new XNumber(new Float(9.88)));		
		assertEquals("9.0", this.floor.eval().toString());

		this.floor.setLeftOperand(new XNumber(new Float(9.08)));		
		assertEquals("9.0", this.floor.eval().toString());

		this.floor.setLeftOperand(null);		
		assertEquals(Double.NaN, this.floor.eval().toNumber());
	}

	@Test
	public void testToBoolean() {
		this.floor.setLeftOperand(new XNumber(new Float(0.1)));
		assertFalse(this.floor.toBoolean());
		
		this.floor.setLeftOperand(new XNumber(new Float(-0.1)));
		assertTrue(this.floor.toBoolean());
	}
	
	@Test
	public void testToString() {
		this.floor.setLeftOperand(new XNumber(new Float(9.8)));
		assertEquals("floor(9.8)", this.floor.toString());
	}

}
