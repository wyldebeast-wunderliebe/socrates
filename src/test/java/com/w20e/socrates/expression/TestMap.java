package com.w20e.socrates.expression;

import junit.framework.TestCase;

public class TestMap extends TestCase {

	Map map;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		
		Minus min = new Minus();
		min.setLeftOperand(new XNumber(0));
		min.setRightOperand(new XNumber(0.1));
		
		Expression[] objs = {new XString("b"), new XString("a"), min, new XString("b"), new XString("xxx")};
		
		this.map = new Map();
		this.map.setOperands(objs);
	}


	public void testToString() {

		Expression[] objs = {new XString("b"), new XString("b"), new XString("xxx")};
		
		this.map = new Map();
		this.map.setOperands(objs);

		assertEquals("map(b, {b=xxx, })", this.map.toString());
	}

	public void testEval() {

		assertEquals("xxx", this.map.eval().toString());
	
		// Erroneous map
		Expression[] objs = {new XString("b"), new XString("a"), new XString("b"), new XString("xxx")};
		
		Map map2 = new Map();
		map2.setOperands(objs);

		assertEquals(null, map2.eval().toObject());

		map2 = new Map();
	
		assertEquals(null, map2.eval().toObject());
	}

	public void testToBoolean() {

	    assertTrue(this.map.toBoolean());

	    Expression[] objs = {new XString("b"), new XString("a"), new XString("pipo"), new XString("b"), new XString("")};
	        
	    this.map = new Map();
	    this.map.setOperands(objs);
	    
	    assertFalse(this.map.toBoolean());
	}
}
