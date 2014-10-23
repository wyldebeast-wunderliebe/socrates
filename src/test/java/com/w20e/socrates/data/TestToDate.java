package com.w20e.socrates.data;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class TestToDate {

    @Before
    public void setUp() throws Exception {
        Locale.setDefault(new Locale("en_US"));
    }

    @Test
    public void testTransform() {

        ToDate todate = new ToDate();
        Date d = new Date();

        try {
            todate.transform(null);
            fail("Should have failed on transform of null");
        } catch (TransformationException tfe) {
            // ok
        }

        try {
            assertNull(todate.transform(""));
            fail("Should have failed on transform of ''");
        } catch (TransformationException tfe) {
            // ok
        }

        try {
            assertNull(todate.transform("pipo"));
            fail("Should have failed on transform of 'pipo'");
        } catch (TransformationException tfe) {
            // ok
        }

        try {
            assertEquals(d, todate.transform(d));

            assertEquals("10", String.format("%tm", todate
                    .transform("10/10/1968")));
        } catch (TransformationException e) {
            fail(e.getMessage());
        }

        Locale l = new Locale("nl_NL");

        assertEquals("01", String.format("%tm", todate.transform("01/04/2009",
                l)));

        l = new Locale("de_DE");

        assertEquals("01", String.format("%tm", todate.transform("01/04/2009",
                l)));

        assertNull(todate.transform("pipo", l));
        assertEquals(d, todate.transform(d, l));
        assertNull(todate.transform(null, l));
    }
}
