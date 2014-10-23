package com.w20e.socrates.expression;

import java.util.List;

import junit.framework.TestCase;

public class TestSample extends TestCase {

    @Override
    protected void setUp() throws Exception {
    }

    public void testToString() {

        Sample s = new Sample();

        XNumber num0 = new XNumber(Long.valueOf(1));
        XNumber num1 = new XNumber(Long.valueOf(2));
        XNumber num2 = new XNumber(Long.valueOf(3));
        XNumber num3 = new XNumber(Long.valueOf(4));
        XNumber num4 = new XNumber(Long.valueOf(5));
        XNumber num5 = new XNumber(Long.valueOf(6));
        XNumber size = new XNumber(Long.valueOf(3));

        Expression[] ops = {num0, num1, num2, num3, num4, num5, size};

        s.setOperands(ops);

        assertEquals("sample(1,2,3,4,5,6,3,)", s.toString());
    }

    public void testEval() {

        Sample s = new Sample();

        XNumber num0 = new XNumber(Long.valueOf(1));
        XNumber num1 = new XNumber(Long.valueOf(2));
        XNumber num2 = new XNumber(Long.valueOf(3));
        XNumber num3 = new XNumber(Long.valueOf(4));
        XNumber num4 = new XNumber(Long.valueOf(5));
        XNumber num5 = new XNumber(Long.valueOf(6));
        XNumber size = new XNumber(Long.valueOf(3));

        Expression[] ops = {num0, num1, num2, num3, num4, num5, size};

        s.setOperands(ops);

        assertEquals(((List) ((XList) s.eval()).toObject()).size(), 3);
        
        System.out.println(s.eval());
        
        Expression[] ops1 = {num0, num1, size};

        s.setOperands(ops1);
        
        assertNull(s.eval().toObject());

    }
    
    public void testToBoolean() {

    	Sample s = new Sample();

        XNumber num0 = new XNumber(Long.valueOf(1));
        XNumber num1 = new XNumber(Long.valueOf(2));
        XNumber num2 = new XNumber(Long.valueOf(3));
        XNumber num3 = new XNumber(Long.valueOf(4));
        XNumber num4 = new XNumber(Long.valueOf(5));
        XNumber num5 = new XNumber(Long.valueOf(6));
        XNumber size = new XNumber(Long.valueOf(3));

        Expression[] ops = {num0, num1, num2, num3, num4, num5, size};

        s.setOperands(ops);

        assertTrue(s.toBoolean());

        Expression[] ops1 = {};

        s.setOperands(ops1);

        assertFalse(s.toBoolean());

    }
}
