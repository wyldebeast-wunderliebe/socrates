package com.w20e.socrates.data;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestXSDate {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEval() {

        XSDate date = new XSDate();
        Date d = new Date();

        try {
            assertNull(date.eval(null).toObject());
            fail("Shouldn't be possible to convert null to date!");
        } catch (RestrictionViolation rve) {
            // ok
        } catch (TransformationException e) {
            // ok
        }

        try {
            assertNull(date.eval("pipo"));
            fail("Shouldn't be possible to convert pipo to date!");
        } catch (RestrictionViolation rve) {
            // ok
        } catch (TransformationException e) {
            // ok
        }

        try {
            assertEquals(d.toString(), date.eval(d).toString());
        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }
    }

}
