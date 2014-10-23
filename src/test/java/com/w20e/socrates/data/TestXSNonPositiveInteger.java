/*
 * Created on Jun 20, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import com.w20e.socrates.data.XSNonPositiveInteger;

import junit.framework.TestCase;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class TestXSNonPositiveInteger extends TestCase {

    public void testSetValue() {

        XSNonPositiveInteger i = new XSNonPositiveInteger();

        try {
            Number j = i.eval(Integer.valueOf(-10)).toNumber();

            assertEquals(j.intValue(), -10);

            j = i.eval("-10").toNumber();

            assertEquals(j.intValue(), -10);
        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

        try {
            i.eval("20").toNumber();
            fail("Should fail with positive int string");
        } catch (Exception e) {
            // As expected.
        }

        try {
            i.eval("0").toNumber();
            fail("Should fail with 0 string");
        } catch (Exception e) {
            // As expected.
        }
    }
}
