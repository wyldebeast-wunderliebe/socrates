/*
 * File      : TestMappingFactory.java
 * Classname : TestMappingFactory
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.5 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.xalan.xsltc.runtime.AttributeList;

import com.w20e.socrates.process.Render;
import com.w20e.socrates.workflow.ActionFactory;
import com.w20e.socrates.workflow.Mapping;
import com.w20e.socrates.workflow.MappingFactory;
import com.w20e.socrates.workflow.ProcessAction;

public class TestMappingFactory extends TestCase {

  private MappingFactory fac;
  private ActionFactory act;
  private AttributeList attr;

  public TestMappingFactory(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.fac = new MappingFactory();
    this.act = new ActionFactory();
    this.attr = new AttributeList();

    this.attr.add("id", "foo");
    this.attr.add("class", "com.w20e.socrates.process.Render");

    try {
      this.act.createObject(this.attr);
    } catch (Exception e) {
      fail();
    }
  }

  public void testCreateObject() {

    Object obj = null;

    try {
      obj = this.fac.createObject(this.attr);
      fail();
    }
    catch (Exception e) {
    	// As expected.
    }

    this.attr.add("from", "foo");
    this.attr.add("to", "foo");
    this.attr.add("condition", "");

    try {
      obj = this.fac.createObject(this.attr);
      fail();
    }
    catch (Exception e) {
    	// As expected.
    }

    this.attr.clear();
    
    this.attr.add("from", "foo");
    this.attr.add("to", "foo");
    this.attr.add("condition", "ok");

    try {
      obj = this.fac.createObject(this.attr);
    }
    catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    assertTrue(obj instanceof Mapping);

    assertTrue(((Mapping) obj).getFrom() instanceof Render);
    assertTrue(((Mapping) obj).getTo() instanceof Render);

    assertEquals(((ProcessAction) ((Mapping) obj).getFrom()).getId(), "foo");
    assertEquals(((ProcessAction) ((Mapping) obj).getTo()).getId(), "foo");

    assertEquals(( (Mapping)obj ).getCondition(), "ok");
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestMappingFactory("testCreateObject"));

    return suite;
  }
}
