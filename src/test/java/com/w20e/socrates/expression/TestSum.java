package com.w20e.socrates.expression;

import junit.framework.TestCase;

public class TestSum extends TestCase {

	private Sum sum;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		this.sum = new Sum();
	}

	public void testToString() {

		assertEquals("sum()", this.sum.toString());

		XNumber num0 = new XNumber(Long.valueOf(5));
		XNumber num1 = new XNumber(Long.valueOf(235));
		
		Expression[] ops = {num0, num1};
		
		this.sum.setOperands(ops);
		
		assertEquals("sum(5,235,)", this.sum.toString());
	}

	public void testEval() {

		XNumber num0 = new XNumber(Long.valueOf(5));
		XNumber num1 = new XNumber(Long.valueOf(235));
		XNumber num2 = new XNumber(Long.valueOf(-40));
		
		Expression[] ops = {num0, num1, num2};
		
		this.sum.setOperands(ops);

		assertEquals(this.sum.eval().toNumber().longValue(), 200);
	}

	public void testToBoolean() {

		assertFalse(this.sum.toBoolean());

		XNumber num0 = new XNumber(Long.valueOf(5));
		XNumber num1 = new XNumber(Long.valueOf(235));
		XNumber num2 = new XNumber(Long.valueOf(-40));
		
		Expression[] ops = {num0, num1, num2};

		this.sum.setOperands(ops);

		assertTrue(this.sum.toBoolean());
	}

}
