package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLength {

    Length l;
    
    @Before
    public void setUp() throws Exception {

        this.l = new Length(10);
    }

    @Test
    public void testEval() {

        assertTrue(this.l.eval("aabbccddee"));
        assertFalse(this.l.eval("bbccddee"));
    }

}
