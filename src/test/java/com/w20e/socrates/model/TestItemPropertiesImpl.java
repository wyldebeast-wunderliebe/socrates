/*
 * File      : TestItemPropertiesImpl.java
 * Classname : TestItemPropertiesImpl
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import junit.framework.TestCase;

import com.w20e.socrates.data.XSBoolean;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.expression.Equals;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.model.ItemPropertiesImpl;

public class TestItemPropertiesImpl extends TestCase {

  private ItemPropertiesImpl props;
  public TestItemPropertiesImpl(String name) {
    super(name);
  }

  public void setUp() {

    this.props = new ItemPropertiesImpl("id");
  }

  public void testSetReadOnly() {

    Equals eq = new Equals();

    this.props.setReadOnly(eq);

    assertEquals(eq, this.props.getReadOnly());
  }

  public void testSetRequired() {

    Equals eq = new Equals();

    this.props.setRequired(eq);

    assertEquals(eq, this.props.getRequired());
  }

  public void testSetRelevant() {

    Equals eq = new Equals();

    this.props.setRelevant(eq);

    assertEquals(eq, this.props.getRelevant());
  }

  public void testGetType() {

    assertEquals(this.props.getType(), XSString.class);

    this.props.setType(XSBoolean.class);

    assertEquals(this.props.getType(), XSBoolean.class);
  }

  public void testGetP3PType() {

    assertNull(this.props.getP3PType());

    this.props.setP3PType("surname");

    assertEquals(this.props.getP3PType(), "surname");
  }

  public void testSetConstraint() {

    assertTrue(this.props.getConstraint().toBoolean());

    Equals eq = new Equals();

    this.props.setConstraint(eq);

    assertEquals(eq, this.props.getConstraint());
  }

  public void testSetCalculate() {

	    assertNull(this.props.getCalculate());

	    XString xstr = new XString("yep");
	    
	    this.props.setCalculate(xstr);

	    assertEquals(xstr, this.props.getCalculate());
	  }

  public void testSetBind() {
	  
	  this.props.setBind("somebind");
	  assertEquals("somebind", this.props.getBind());
  }
}
