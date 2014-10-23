package com.w20e.socrates.expression;

import junit.framework.TestCase;

public class TestRandomInt extends TestCase {

    @Override
    protected void setUp() throws Exception {
    }

    public void testToString() {

        RandomInt r = new RandomInt();

        XNumber num0 = new XNumber(Long.valueOf(5));
        XNumber num1 = new XNumber(Long.valueOf(235));

        Expression[] ops = {num0, num1};

        r.setOperands(ops);

        assertEquals("random(5,235,)", r.toString());

        Expression[] ops2 = {num0};

        r.setOperands(ops2);

        assertEquals("random(5,)", r.toString());

        Expression[] ops3 = {};

        r.setOperands(ops3);

        assertEquals("random()", r.toString());

    }

    public void testEval() {

        RandomInt r = new RandomInt();

        XNumber num0 = new XNumber(Long.valueOf(5));
        XNumber num1 = new XNumber(Long.valueOf(15));

        Expression[] ops = {num0, num1};

        r.setOperands(ops);

        assertTrue(r.eval().toNumber().intValue() >= 5 && r.eval().toNumber().intValue() < 15);

        Expression[] ops2 = {num0};

        r.setOperands(ops2);

        assertTrue(r.eval().toNumber().intValue() >= 0 && r.eval().toNumber().intValue() < 5);

        Expression[] ops3 = {new XNumber(-10), new XNumber(-1)};

        r.setOperands(ops3);
        
        assertTrue(r.eval().toNumber().intValue() >= -10 && r.eval().toNumber().intValue() < -1);
        
        Expression[] ops4 = {};
        r.setOperands(ops4);
        
        assertFalse(Undef.UNDEF.equals(r.eval().toNumber()));
    }
    
    public void testToBoolean() {
        RandomInt r = new RandomInt();
        
        Expression[] ops = {new XNumber(0), new XNumber(0)};
        r.setOperands(ops);
        
        assertFalse(r.toBoolean());

        Expression[] ops1 = {new XNumber(10), new XNumber(1)};
        r.setOperands(ops1);
        
        assertFalse(r.toBoolean());

        Expression[] ops2 = {new XNumber(1), new XNumber(10)};
        r.setOperands(ops2);
        
        assertTrue(r.toBoolean());
    }
}
