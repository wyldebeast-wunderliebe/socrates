package com.w20e.socrates.data;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class TestToString {

     ToString tostr;   
    
    @Before
    public void setUp() throws Exception {
        
        this.tostr = new ToString();
    }

    @Test
    public void testTransformObject() {

        assertEquals("pipo", this.tostr.transform("pipo"));
        assertNull(this.tostr.transform(null));
        assertEquals("您将有机会通过抽奖获得您所在地区最低", this.tostr.transform("您将有机会通过抽奖获得您所在地区最低"));
    }

    @Test
    public void testTransformObjectLocale() {
        assertEquals("pipo", this.tostr.transform("pipo", Locale.ENGLISH));
    }

}
