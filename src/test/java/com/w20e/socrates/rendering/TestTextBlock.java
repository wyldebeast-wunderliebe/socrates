package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTextBlock {

    TextBlock tb;
    
    @Before
    public void setUp() throws Exception {
    
        this.tb = new TextBlock("#ID#");
    }

    @Test
    public void testGetId() {

        assertEquals("#ID#", this.tb.getId());
    }

    @Test
    public void testGetType() {

        assertEquals("text", this.tb.getType());
    }

    @Test
    public void testGetText() {

        this.tb.setText(new TranslatableImpl("TEXT"));
        assertEquals("TEXT", this.tb.getText().toString());
    }

}
