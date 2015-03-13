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
    public void testGetMax() {

        this.range.setMax(10);
        assertEquals(10, this.range.getMax());
    }

    @Test
    public void testGetMin() {
        this.range.setMin(1);
        assertEquals(1, this.range.getMin());
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
    
    public final void testGetOptions() {

    	this.range.setMin(1);
    	this.range.setMax(10);
    	
    	assertEquals(10, this.range.getOptions().size());
    	
    	this.range.setStep(2);
    	
    	assertEquals(5, this.range.getOptions().size());
    }

}
