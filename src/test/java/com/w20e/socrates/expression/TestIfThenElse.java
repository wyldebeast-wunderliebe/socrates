package com.w20e.socrates.expression;

import junit.framework.TestCase;

public class TestIfThenElse extends TestCase {

	private IfThenElse op;
	private XNumber nv;
	private XString sv;
	private XBoolean bv;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		this.op = new IfThenElse();
		this.nv = new XNumber(Integer.valueOf(0));
		this.sv = new XString("ok");
		this.bv = new XBoolean(true);
	}

	public void testToString() {

		this.op.setLeftOperand(this.bv);
		this.op.setMiddleOperand(this.nv);
		this.op.setRightOperand(this.sv);
		
		assertEquals("true ? 0 : ok", this.op.toString());
	}

	public void testToBoolean() {

		this.op.setLeftOperand(new XBoolean(true));
		this.op.setMiddleOperand(new XBoolean(false));
		this.op.setRightOperand(new XBoolean(true));

		assertFalse(this.op.toBoolean());

		this.op.setLeftOperand(new XBoolean(false));

		assertTrue(this.op.toBoolean());
	}

	public void testEval() {

		this.op.setLeftOperand(this.bv);
		this.op.setMiddleOperand(this.nv);
		this.op.setRightOperand(this.sv);
		
		assertEquals(0, this.op.eval().toNumber().intValue());

		this.op.setLeftOperand(new XBoolean(false));
		
		assertEquals("ok", this.op.eval().toString());
	}

	/*
	 * Add some ternary operation impl tests...
	 */
	public void testGetLeftOperand() {

	    this.op.setLeftOperand(this.bv);
	    assertEquals(this.bv, this.op.getLeftOperand());
	    
	    this.op.setLeftOperand(null);
	    assertEquals(Undef.UNDEF.toString(), this.op.getLeftOperand().toString());
	}

    public void testGetRightOperand() {

        this.op.setRightOperand(this.bv);
        assertEquals(this.bv, this.op.getRightOperand());
        
        this.op.setRightOperand(null);
        assertEquals(Undef.UNDEF.toString(), this.op.getRightOperand().toString());
    }

    public void testGetMiddleOperand() {

        this.op.setMiddleOperand(this.bv);
        assertEquals(this.bv, this.op.getMiddleOperand());
        
        this.op.setMiddleOperand(null);
        assertEquals(Undef.UNDEF.toString(), this.op.getMiddleOperand().toString());
    }

}
