/*
 * Created on Apr 6, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.util;

import java.util.Locale;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author dokter Unit test for locale utility.
 */
public class TestLocaleUtility {

    @Test
    public void testConstructor() {

        try {
            LocaleUtility.class.newInstance();
            fail("Should not be possible to instantiate LocaleUtility");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    public void testGetLocale() {

        String localeStr = null;

        assertNull(LocaleUtility.getLocale(localeStr, false));
        assertEquals(LocaleUtility.getLocale(localeStr, true), new Locale("en", "GB"));

        localeStr = "";

        assertNull(LocaleUtility.getLocale(localeStr, false));

        localeStr = "nl";

        assertEquals(new Locale("nl"), LocaleUtility.getLocale(localeStr, false));

        localeStr = "nl_";

        assertEquals(new Locale("nl"), LocaleUtility.getLocale(localeStr, false));

        localeStr = "nl_NL";

        assertEquals(LocaleUtility.getLocale(localeStr, false), new Locale("nl", "NL"));

        localeStr = "nl_NL_informal";

        assertEquals(LocaleUtility.getLocale(localeStr, false), new Locale("nl", "NL",
                "informal"));

    }
}
