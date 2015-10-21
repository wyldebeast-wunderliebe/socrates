/*
 * Created on Jun 20, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import com.w20e.socrates.data.XSInteger;

import junit.framework.TestCase;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class TestXSInteger extends TestCase {

    public void testEval() {

        try {
            XSInteger i = new XSInteger();

            Number j = i.eval(Integer.valueOf(10)).toNumber();

            assertEquals(j.intValue(), 10);

            j = i.eval("10").toNumber();

            assertEquals(j.intValue(), 10);

            j = i.eval("10.0").toNumber();

            assertEquals(j.intValue(), 10);

            j = i.eval(new Float(10.0)).toNumber();

            assertEquals(j.intValue(), 10);

            j = i.eval(Integer.valueOf(400000123)).toNumber();

            assertEquals(j.intValue(), 400000123);
        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

        try {
            XSInteger i = new XSInteger();
            System.out.println(i.eval("").toNumber());
            fail("Should fail with value not conform restriction");
        } catch (Exception e) {
            //e.printStackTrace();
            // Whatever.
        }

        try {
            XSInteger i = new XSInteger();
            i.eval("pipo").toNumber();
            fail("Should fail with non int string");
        } catch (Exception e) {
            //e.printStackTrace();
            // Whatever.
        }

    
    }
}
