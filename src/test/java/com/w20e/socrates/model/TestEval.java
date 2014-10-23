package com.w20e.socrates.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XString;

public class TestEval {

	
	private Eval eval;
	
	@Before
	public void setUp() throws Exception {
		
		this.eval = new Eval("1 + 2", null, null);
	}

	@Test
	public void testToString() {

		assertEquals("sum(1,2,)", this.eval.toString());
	}

	@Test
	public void testSetLeftOperand() {
		
		this.eval.setLeftOperand(new XString("'moi'"));

		assertEquals("moi", this.eval.getLeftOperand().toString());

		this.eval.setLeftOperand(null);

		assertEquals(null, this.eval.getLeftOperand().eval().toObject());

		this.eval.setLeftOperand(new XString("2 + 'x' *"));
		
		assertEquals(null, this.eval.getLeftOperand().eval().toObject());
	}

	
	@Test
	public void testEvalConstructor() {
		
		Eval eval2 = new Eval("1 - 3 *", null, null);
		
		assertEquals(null, eval2.getLeftOperand().eval().toObject());
	}

	
	@Test
	public void testToBoolean() {

		assertTrue(this.eval.toBoolean());
		
		this.eval.setLeftOperand(new XBoolean(false));
		
		assertFalse(this.eval.toBoolean());
	}

	@Test
	public void testEval() {

		this.eval.setLeftOperand(new XString("'moi'"));

		assertEquals("moi", this.eval.eval().toString());

		this.eval.setLeftOperand(null);

		assertEquals(null, this.eval.eval().toObject());
	}

}
