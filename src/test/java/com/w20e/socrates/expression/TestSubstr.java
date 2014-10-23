package com.w20e.socrates.expression;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSubstr {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {
        Substr sstr = new Substr();
        assertNull(sstr.eval().toObject());

        Expression[] args = { new XString("pipo") };
        sstr.setOperands(args);

        assertEquals("pipo", sstr.eval().toString());

        Expression[] args1 = { new XString("mamaloe"),
                new XNumber(Double.valueOf(2)) };
        sstr.setOperands(args1);

        assertEquals("maloe", sstr.eval().toString());

        Expression[] args2 = { new XString("mamaloe"),
                new XNumber(Double.valueOf(2)), new XNumber(Double.valueOf(5)) };
        sstr.setOperands(args2);

        assertEquals("mal", sstr.eval().toString());
        
        Expression[] args3 = { new XString("pipo"),
                new XNumber(Double.valueOf(2)), new XNumber(Double.valueOf(8)) };
        sstr.setOperands(args3);

        assertEquals("po", sstr.eval().toString());

        Expression[] args4 = { new XString("pipo"),
                new XNumber(Double.valueOf(5)), new XNumber(Double.valueOf(8)) };
        sstr.setOperands(args4);

        assertEquals("", sstr.eval().toString());

    }
    
    @Test
    public void testToBoolean() {

        Substr sstr = new Substr();
        assertNull(sstr.eval().toObject());

        Expression[] args = { new XString("pipo") };
        
        sstr.setOperands(args);

        assertTrue(sstr.toBoolean());

        Expression[] args2 = { new XString("pipo"), new XNumber(new Integer(0)), new XNumber(new Integer(0))};
        
        sstr.setOperands(args2);

        assertFalse(sstr.toBoolean());
}
    
    @Test
    public void testToString() {
    
        Substr sstr = new Substr();
        assertNull(sstr.eval().toObject());

        Expression[] args = { new XString("pipo") };
        
        sstr.setOperands(args);

    	assertEquals("substr(pipo,)", sstr.toString());
    }

}
