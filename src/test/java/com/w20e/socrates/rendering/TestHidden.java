package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestHidden {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testHidden() {

        Hidden hidden = new Hidden("hide_me");
        assertEquals("hidden", hidden.getType());
        assertEquals("hide_me", hidden.getId());
    }

}
