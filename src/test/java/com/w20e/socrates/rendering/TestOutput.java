package com.w20e.socrates.rendering;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.data.XSEmail;
import com.w20e.socrates.data.XSInteger;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.expression.XString;

public class TestOutput {

    Output out;
    
    @Before
    public void setUp() throws Exception {

        this.out = new Output("#ID#");
    }

    @Test
    public void testProcessInput() {

        assertEquals(null, this.out.processInput(null));
    }

    @Test
    public void testGetDisplayValue() {
        assertEquals("", this.out.getDisplayValue(null, XSString.class, null));
        assertEquals("xxx", this.out.getDisplayValue(new XString("xxx"), XSString.class, null));
        assertEquals("", this.out.getDisplayValue(new XString("xxx"), XSInteger.class, null));
        assertEquals("", this.out.getDisplayValue(new XString("xxx"), XSEmail.class, null));
    }

}
