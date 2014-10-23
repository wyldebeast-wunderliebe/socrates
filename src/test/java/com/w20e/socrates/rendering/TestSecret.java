package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSecret {

    Secret control;
    
    @Before
    public void setUp() throws Exception {
        
        this.control = new Secret("#ID#");
    }

    @Test
    public void testGetId() {

        assertEquals("#ID#", this.control.getId());
    }

    public void testGetType() {

         assertEquals("secret", this.control.getType());
    }

}
