/*
 * File      : TestProcessContextImpl.java
 * Classname : TestProcessContextImpl
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.4 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import com.w20e.socrates.workflow.ProcessContextImpl;

import junit.framework.TestCase;

public class TestProcessContextImpl extends TestCase {

  private ProcessContextImpl ctx;

  public TestProcessContextImpl(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.ctx = new ProcessContextImpl();
  }

  public void testGetProperty() {

    this.ctx.setProperty("bar", "pipo");

    String defVal = "DEFAULT";

    assertEquals(this.ctx.getProperty("foo", defVal), defVal);

    assertEquals(this.ctx.getProperty("bar", defVal), "pipo");
  }

  public void testPutAll() {
   
    ProcessContextImpl ctx2 = new ProcessContextImpl();
    ctx2.setProperty("foo", "bar");
    ctx2.setProperty("bar", "pipo");
    
    this.ctx.addContext(ctx2);
    
    assertEquals("bar", this.ctx.getProperty("foo"));
    assertEquals("pipo", this.ctx.getProperty("bar"));
  }
}
