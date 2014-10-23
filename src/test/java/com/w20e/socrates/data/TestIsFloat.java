package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestIsFloat {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {

        Float f = new Float(10.0);
        IsFloat isfloat = new IsFloat();
        assertTrue(isfloat.eval(f));
        assertFalse(isfloat.eval(""));
    }

}
