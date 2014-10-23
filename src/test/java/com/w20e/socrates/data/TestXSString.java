/*
 * Created on Jun 21, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.expression.Undef;

import junit.framework.TestCase;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class TestXSString extends TestCase {

    public void testEval() {

        XSString str = new XSString();

        try {
            assertNull(str.eval(null).toObject());

            assertEquals(Undef.UNDEF.toString(), str.eval(str.eval(null))
                    .toObject().toString());

            assertEquals("xxx", str.eval("xxx").toString());

            assertEquals("10", str.eval(Integer.valueOf(10)).toString());

            // Add datatypeimpl test
            assertEquals("", str.evalLexical(Undef.UNDEF, Locale.US));
        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

    }


    public void testEvalLexical() {

        XSString str = new XSString();

        try {
            assertEquals("", str.evalLexical(null, null));
            assertEquals("xxx", str.evalLexical("xxx", null));
            assertEquals("10", str.evalLexical(Integer.valueOf(10), null));
            assertEquals("10.45", str.evalLexical(Double.valueOf(10.45), null));

        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

    }
}
