/*
 * Created on Jun 23, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.data;

import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class TestToFloat extends TestCase {

    public void testTransform() {

        ToFloat fl = new ToFloat();

        assertEquals(new Float(3.25), fl.transform("3.25"));
        assertEquals("3.25", fl.transform("3,25", Locale.FRANCE).toString());
        assertNull(fl.transform("pipo", Locale.FRANCE));        
    }

}
