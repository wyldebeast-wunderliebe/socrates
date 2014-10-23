package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.data.ToInteger;

import junit.framework.TestCase;

public class TestToInteger extends TestCase {

    private ToInteger i;

    protected void setUp() throws Exception {
        super.setUp();

        this.i = new ToInteger();
    }

    /*
     * Test method for 'com.w20e.socrates.dataimpl.ToInteger.transform(Object)'
     */
    public void testTransform() {

        try {
            assertEquals(Integer.valueOf(1), this.i.transform("1"));
            assertEquals(Integer.valueOf(1), this.i.transform("1.0"));
            assertEquals(Integer.valueOf(1), this.i.transform(new Float(1.0)));
            assertEquals(Integer.valueOf(1), this.i.transform(new Float(1.3)));
            assertEquals(Integer.valueOf(1), this.i.transform(new Float(1.7)));
            assertEquals(Integer.valueOf(1), this.i.transform(new Float(1.7),
                    Locale.ENGLISH));
        } catch (TransformationException e) {
            fail(e.getMessage());
        }

        try {
            this.i.transform("FOO");
            fail("Shouldn't be able to convert FOO to integer");
        } catch (TransformationException e) {
            // as expected
        }

    }

}
