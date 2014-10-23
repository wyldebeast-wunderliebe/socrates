package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDataTypeViolation {

    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testConstructor() {
        DataTypeViolation dtv = new DataTypeViolation("Argh");
        assertEquals("Argh", dtv.getMessage());
    }

}
