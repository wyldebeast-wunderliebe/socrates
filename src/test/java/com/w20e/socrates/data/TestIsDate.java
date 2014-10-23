package com.w20e.socrates.data;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestIsDate {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {

        IsDate isdate = new IsDate();
        Date date = new Date();
        assertTrue(isdate.eval(date));
        assertFalse(isdate.eval(""));
    }

}
