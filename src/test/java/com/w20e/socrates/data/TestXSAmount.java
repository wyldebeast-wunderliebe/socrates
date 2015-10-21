package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.data.XSAmount;

import junit.framework.TestCase;

public class TestXSAmount extends TestCase {

    /*
     * Test method for 'com.w20e.socrates.dataimpl.DataTypeImpl.eval(Object)'
     */
    public void testEval() {

        XSAmount amount = new XSAmount();

        try {        	
        	assertEquals("1.0", amount.eval("1").toString());
            assertEquals("$1.00", amount.evalLexical("1", Locale.US));
            assertEquals(new Float("10.00").toString(), amount.eval("10")
                    .toString());
            assertEquals("10.005", amount.eval("10.005").toString());
            assertEquals("$10.01", amount.evalLexical("10.005", Locale.US));
            // assertEquals(Currency.getInstance("EUR").getSymbol() + " 10,00",
            // amount.evalLexical("10.004", new Locale("nl", "NL", "€")));
            assertEquals("$0.00", amount.evalLexical("0", Locale.US));
            assertEquals("$0.00", amount.evalLexical("0.0", Locale.US));
            //assertEquals("$0.00", amount.evalLexical(null, Locale.US));

            assertEquals("R$ 3,14", amount.evalLexical(3.14, new Locale("pt", "BR")));
            assertEquals("R$ 3.014,99", amount.evalLexical("3.014,99", new Locale("pt", "BR")));
            assertEquals("R$ 3,10", amount.evalLexical("3,1", new Locale("pt", "BR")));

        } catch (TransformationException e) {
            fail(e.getMessage());
        } catch (RestrictionViolation e) {
            fail(e.getMessage());
        }

    }

}
