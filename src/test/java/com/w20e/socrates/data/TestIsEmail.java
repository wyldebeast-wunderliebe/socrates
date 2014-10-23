package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestIsEmail {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {

        IsEmail email = new IsEmail();
        
        assertTrue(email.eval("pipo@w20e.com"));
        assertTrue(email.eval("pipo@w20e.com.au"));
        assertFalse(email.eval("@w20e.com.au"));
        assertFalse(email.eval("pipo@au"));
    }

}
