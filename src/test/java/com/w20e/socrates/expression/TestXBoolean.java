/*******************************************************************************
 *
 * File      : TestXBoolean.java
 * Author    : Duco Dokter
 * Created   : Mon Jul 26 11:38:54 2004
 * Version   : $Id: TestXBoolean.java,v 1.8 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 *
 ******************************************************************************/

package com.w20e.socrates.expression;

import com.w20e.socrates.expression.XBoolean;

import junit.framework.TestCase;

public class TestXBoolean extends TestCase {

    private XBoolean bool;

    public TestXBoolean(String name) {

        super(name);
    }

    public void setUp() {

        this.bool = new XBoolean(true);
    }

    public void testEquals() {

        XBoolean ok = new XBoolean(true);

        assertTrue(this.bool.equals(ok));

        ok = new XBoolean(false);

        assertFalse(this.bool.equals(ok));

        assertFalse(this.bool.equals(null));

        assertFalse(this.bool.equals("pipo"));

        assertFalse(this.bool.equals(Undef.UNDEF));
    }

    public void testGetType() {

        assertEquals("XBoolean", this.bool.getType());
    }

    public void testToBoolean() {

        assertTrue(this.bool.toBoolean());

        this.bool = new XBoolean(null);

        assertFalse(this.bool.toBoolean());

        this.bool = new XBoolean(false);

        assertFalse(this.bool.toBoolean());

        this.bool = new XBoolean(Boolean.valueOf(false));

        assertFalse(this.bool.toBoolean());

        this.bool = new XBoolean(Boolean.valueOf(true));

        assertTrue(this.bool.toBoolean());
    }

    public void testToString() {

        assertEquals(this.bool.toString(), "true");

        assertEquals((new XBoolean(false)).toString(), "false");
    }

    public void testToNumber() {

        assertEquals(this.bool.toNumber(), new Double(1));

        this.bool = new XBoolean(false);

        assertEquals(this.bool.toNumber(), new Double(0));
    }

    public void testCompareTo() {

        assertTrue(this.bool.compareTo(new XBoolean(true)) == 0);
        assertTrue(this.bool.compareTo(new XBoolean(false)) > 0);

        this.bool = new XBoolean(false);

        assertTrue(this.bool.compareTo(new XBoolean(true)) < 0);
        assertTrue(this.bool.compareTo(new XBoolean(false)) == 0);

        assertTrue(this.bool.compareTo(Undef.UNDEF) == -1);
    }

    public void testEval() {

        assertEquals(this.bool, this.bool.eval());
    }

    public void testToObject() {

        this.bool = new XBoolean(false);

        assertFalse(((Boolean) this.bool.toObject()).booleanValue());

        this.bool = new XBoolean(true);

        assertTrue(((Boolean) this.bool.toObject()).booleanValue());

    }
}
