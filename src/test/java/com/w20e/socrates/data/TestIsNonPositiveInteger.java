package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestIsNonPositiveInteger {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {

        Integer int0 = Integer.valueOf(1);
        Integer int1 = Integer.valueOf(-1);
        IsNonPositiveInteger isint = new IsNonPositiveInteger();
        
        assertTrue(isint.eval(int1));
        assertFalse(isint.eval(int0));
        assertFalse(isint.eval(""));
    }

}
