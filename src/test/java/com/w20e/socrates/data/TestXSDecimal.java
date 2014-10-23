/*
 * Created on Jul 28, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.data.XSDecimal;

import junit.framework.TestCase;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class TestXSDecimal extends TestCase {

    public void testEval() {

        try {
            XSDecimal decimal = new XSDecimal();

            Number j = decimal.eval(new Float(10)).toNumber();

            assertEquals(j.intValue(), 10);

            j = decimal.eval("10").toNumber();

            assertEquals(j.intValue(), 10);

            j = decimal.eval("10.3").toNumber();

            assertEquals(j.intValue(), 10);

            
            
            
        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

        try {
            XSDecimal i = new XSDecimal();

            i.eval("pipo").toNumber();
            fail("Should fail with non float string");
        } catch (Exception e) {
            // Whatever.
        }
    }
    
    
    public void testEvalLexical() {

    XSDecimal dec = new XSDecimal();

    try {

        assertEquals("1.0", dec.eval("1").toString());

        assertEquals("1.0", dec.eval("1.0").toString());
        assertEquals("1", dec.evalLexical("1.0", Locale.US));
        assertEquals("3.14", dec.evalLexical("3.14", Locale.US));
        assertEquals("3,14", dec.evalLexical("3.14", new Locale("pt", "BR")));
        assertEquals("3", dec.evalLexical("3.0", new Locale("pt", "BR")));

    } catch (TransformationException e) {
        fail(e.getMessage());
    } catch (RestrictionViolation e) {
        fail(e.getMessage());
    }

}


}
