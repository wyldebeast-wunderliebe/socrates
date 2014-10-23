/*
 * File      : TestRenderItem.java
 * Classname : TestRenderItem
 * Author    : helmantel
 * Date      : Jan 28, 2005
 * Version   : $Revision: 1.1 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.rendering;

import java.util.Locale;

import junit.framework.TestCase;

import com.w20e.socrates.data.XSDecimal;
import com.w20e.socrates.data.XSEmail;
import com.w20e.socrates.data.XSInteger;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.expression.XString;

/**
 * @author helmantel
 * 
 * Test for a RenderItem (now still very simple, but wait until refactoring).
 */
public class TestInput extends TestCase {

	private ControlImpl item;

	/**
	 * Constructor for TestRenderItem.
	 * 
	 * @param name
	 */
	public TestInput(String name) {
		super(name);
	}

	public void setUp() {
		this.item = new Input("#ID#");
	}

	public final void testRenderItem() {
		this.item = new Input("#IDx#");
		assertEquals(this.item.getId(), "#IDx#");
	}

	public final void testSetType() {
		this.item.setType("type0");
		assertEquals(this.item.getType(), "type0");
	}

	public final void testGetAlert() {
	    this.item.setAlert("pipo");
	    assertEquals("pipo", this.item.getAlert());
	}

    public final void testGetHelp() {
        this.item.setHelp("HELP!");
        assertEquals("HELP!", this.item.getHelp());
    }

    public final void testGetHint() {
        this.item.setHint("HINT!");
        assertEquals("HINT!", this.item.getHint());
    }

    public final void testGetLabel() {
        this.item.setLabel("LBL!");
        assertEquals("LBL!", this.item.getLabel());
    }

    public final void testGetDisplayValue() {
        
        assertEquals("", this.item.getDisplayValue(null, XSString.class, null));
        assertEquals("xxx", this.item.getDisplayValue(new XString("xxx"), XSString.class, null));
        assertEquals("3.1", this.item.getDisplayValue(new Float(3.1), XSDecimal.class, Locale.CANADA));
        assertEquals("3,1", this.item.getDisplayValue(new Float(3.1), XSDecimal.class, Locale.GERMAN));
        assertEquals("", this.item.getDisplayValue(new XString("xxx"), XSInteger.class, null));
        assertEquals("", this.item.getDisplayValue(new XString("xxx"), XSEmail.class, null));
        
        // Let's see how unicode is handled...
        String chinese = "您将有机会通过抽奖获得您所在地区最低";
 
        assertEquals(chinese, this.item.getDisplayValue(chinese, XSString.class, null));
    }
}