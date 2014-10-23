package com.w20e.socrates.expression;


import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class TestNum {

    @Before
    public void setUp() throws Exception {
    }

	@Test
    public void testEval() {
        
        Num num = new Num();
        Expression[] objs = {new XString("yyyy")};
        DateTime dt = new DateTime();
        dt.setOperands(objs);

        num.setLeftOperand(new XString("2009"));
        
        assertEquals(2009, num.eval().toNumber().intValue());
        
        num.setLeftOperand(new XString("pipo"));
        
        assertEquals(Double.NaN, num.eval().toNumber().doubleValue(), 0);
        
        num.setLeftOperand(new XString(null));

        assertEquals(Double.NaN, num.eval().toNumber().doubleValue(), 0);
	}
    
    @Test
    public void testToString() {

    	Num num = new Num();
    	num.setLeftOperand(new XString("pipo"));
    	
    	assertEquals("num(pipo)", num.toString());
    }

    @Test
    public void testToBoolean() {

    	Num num = new Num();
    	num.setLeftOperand(new XString("1"));
    	assertTrue(num.toBoolean());

    	num.setLeftOperand(new XString("0"));
    	assertFalse(num.toBoolean());
    }

}
