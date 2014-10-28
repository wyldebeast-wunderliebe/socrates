package com.w20e.socrates.expression;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Before;

public class TestIn extends TestCase {

	In in;
	
	@Override
	@Before
	public void setUp() throws Exception {
		
		this.in = new In();
	}

    public void testToString() {
    	
        XNumber num = new XNumber(Long.valueOf(3));
        XNumber op0 = new XNumber(Long.valueOf(1));
        XNumber op1 = new XNumber(Long.valueOf(2));
       
    	Expression[] list = {op0, op1, num};
    	
        XList xlist = new XList(Arrays.asList(list));

    	Expression[] ops = {num, xlist};

    	this.in.setOperands(ops);
    	    	
    	assertEquals("in(3,[1, 2, 3],)", this.in.toString());
    }
    
    public void testEval() {

    	XNumber num = new XNumber(Long.valueOf(3));
        XNumber op0 = new XNumber(Long.valueOf(1));
        XNumber op1 = new XNumber(Long.valueOf(2));
       
    	Expression[] list = {op0, op1, num};
    	
        XList xlist = new XList(Arrays.asList(list));

    	Expression[] ops = {num, xlist};

    	this.in.setOperands(ops);
    	
    	assertTrue(this.in.eval().toBoolean());
    	
    	Expression[] list1 = {op0, op1};
    	
        xlist = new XList(Arrays.asList(list1));

    	Expression[] ops1 = {num, xlist};

    	this.in.setOperands(ops1);
    	
    	assertFalse(this.in.eval().toBoolean());

    	// test with separate operands
    	Expression[] ops2 = {num, op0, op1, num};

    	this.in.setOperands(ops2);
    	
    	assertTrue(this.in.eval().toBoolean());

    }
}
