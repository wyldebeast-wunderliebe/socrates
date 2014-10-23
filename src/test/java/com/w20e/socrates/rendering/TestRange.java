package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.data.XSString;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XString;

public class TestRange {

    Range range;
    
    @Before
    public void setUp() throws Exception {

        this.range = new Range("#ID#");
    }

    @Test
    public void testGetEnd() {

        this.range.setEnd(10);
        assertEquals(10, this.range.getEnd());
    }

    @Test
    public void testGetStart() {
        this.range.setStart(1);
        assertEquals(1, this.range.getStart());
    }

    @Test
    public void testGetStep() {
        this.range.setStep(2);
        assertEquals(2, this.range.getStep());

    }

    public final void testGetDisplayValue() {
        
        assertEquals("", this.range.getDisplayValue(null, null, null));
        assertEquals("", this.range.getDisplayValue(Undef.UNDEF, XSString.class, null));

        assertEquals("xxx", this.range.getDisplayValue(new XString("xxx"), XSString.class, null));
    }

}
