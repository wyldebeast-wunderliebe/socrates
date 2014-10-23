/*
 * File      : TestNodeImpl.java
 * Classname : TestNodeImpl
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import com.w20e.socrates.model.NodeImpl;

import junit.framework.TestCase;

public class TestNodeImpl extends TestCase {

  private NodeImpl node;

  public TestNodeImpl(String name) {
    super(name);
  }

  public void setUp() {

    this.node = new NodeImpl("foo");
  }

  public void testConstructor() {

    this.node = new NodeImpl("foo", "yyy");

    assertEquals("foo", this.node.getName());

    assertTrue(this.node.getValue().equals("yyy"));

    this.node = new NodeImpl("foo");

    assertEquals("foo", this.node.getName());

    assertNull(this.node.getValue());

    this.node = new NodeImpl("foo");

    assertEquals("foo", this.node.getName());

    assertNull(this.node.getValue());
  }

  public void testSetValue() {

    this.node.setValue("xxx");

    assertTrue(this.node.getValue().equals("xxx"));
  }

  public void testSetDefaultValue() {

	    this.node.setDefaultValue("yyy");
	    this.node.setValue("xxx");
	    assertTrue(this.node.getValue().equals("xxx"));
	    this.node.reset();
	    assertTrue(this.node.getValue().equals("yyy"));	    
	  }

}
