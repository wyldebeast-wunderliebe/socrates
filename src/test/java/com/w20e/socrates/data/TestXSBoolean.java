package com.w20e.socrates.data;

import com.w20e.socrates.data.XSBoolean;
import com.w20e.socrates.expression.Undef;

import junit.framework.TestCase;

public class TestXSBoolean extends TestCase {

    /*
     * Test method for 'com.w20e.socrates.dataimpl.DataTypeImpl.eval(Object)'
     */
    public void testEval() {

        assertTrue(true);

        XSBoolean bool = new XSBoolean();

        try {
            assertFalse(bool.eval("").toBoolean());
            assertFalse(bool.eval("FaLse").toBoolean());
            assertFalse(bool.eval(null).toBoolean());
            assertFalse(bool.eval(Integer.valueOf(0)).toBoolean());
            assertFalse(bool.eval(Boolean.FALSE).toBoolean());
            assertFalse(bool.eval(Undef.UNDEF).toBoolean());
            assertTrue(bool.eval("x").toBoolean());
            assertTrue(bool.eval("trUe").toBoolean());
            assertTrue(bool.eval(Integer.valueOf(10)).toBoolean());
            assertTrue(bool.eval(Boolean.TRUE).toBoolean());
        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

    }

}
