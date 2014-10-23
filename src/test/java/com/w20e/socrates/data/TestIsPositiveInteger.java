package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestIsPositiveInteger {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {

        Integer int0 = Integer.valueOf(1);
        Integer int1 = Integer.valueOf(-1);
        IsPositiveInteger isint = new IsPositiveInteger();
        
        assertTrue(isint.eval(int0));
        assertFalse(isint.eval(int1));
        assertFalse(isint.eval(""));
    }

}
