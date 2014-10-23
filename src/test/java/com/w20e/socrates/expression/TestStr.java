package com.w20e.socrates.expression;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestStr {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testToString() {

        Str str = new Str();
        str.setLeftOperand(new XString("pipo"));
        
        assertEquals("str(pipo)", str.toString());
    }

    @Test
    public void testEval() {
        Expression[] objs1 = {new XString("01-04-2009"), new XString("dd-MM-yyyy")};

        Str str = new Str();
        DateTime dt = new DateTime();
        dt.setOperands(objs1);

        str.setLeftOperand(dt);
        
        assertEquals("datetime(01-04-2009,dd-MM-yyyy,)", str.eval().toString());
    }

    @Test
    public void testToBoolean() {
        
        Str str = new Str();
        
        str.setLeftOperand(new XString(""));
        
        assertFalse(str.toBoolean());

        str.setLeftOperand(new XString("pipo"));
        
        assertTrue(str.toBoolean());
    }

}
