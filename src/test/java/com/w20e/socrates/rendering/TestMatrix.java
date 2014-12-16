package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMatrix {

    Matrix mx;
    
    @Before
    public void setUp() throws Exception {
        
        this.mx = new Matrix("mx0");
    }

    @Test
    public void testMatrixConstructor() {

        OptionList options = new OptionList();
        options.add("val", new Option("val", "lbl"));
        Matrix mx0 = new Matrix("mx0", options);
        assertEquals(options.getOptions(), mx0.getOptions());
        assertEquals("mx0", mx0.getId());
    }
    
    @Test
    public void testAddOption() {
        OptionList options = new OptionList();
        
        this.mx.setOptions(options);

        this.mx.addOption(new Option("val", "label"));
        
        assertEquals(1, this.mx.getOptions().size());
    }

    @Test
    public void testGetOptions() {
        OptionList options = new OptionList();
        
        this.mx.setOptions(options);
        assertEquals(options.getOptions(), this.mx.getOptions());
    }

    @Test
    public void testGetOptionLabel() {

        this.mx.addOption(new Option("val0", "label0"));
        this.mx.addOption(new Option("val1", "label1"));
        
        assertEquals("label0", this.mx.getOptionLabel("val0").toString());
        assertEquals("label1", this.mx.getOptionLabel("val1").toString());
    }

}
