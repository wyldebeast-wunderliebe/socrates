/*
 * File      : TestMapping.java
 * Classname : TestMapping
 * Author    : Duco Dokter
 * Created   : Thu Feb  3 21:01:03 2005
 * Version   : $Revision: 1.2 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.ActionResultImpl;
import com.w20e.socrates.workflow.Mapping;
import com.w20e.socrates.workflow.ProcessAction;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.ProcessContext;
import com.w20e.socrates.workflow.Success;

public class TestMapping extends TestCase {

  public TestMapping(String name) {
    super(name);
  }

  public void testConstructor() {

    ProcessAction action0 = new Action0("a0");
    ProcessAction action1 = new Action0("a1");

    Mapping mapping = new Mapping(action0, action1, ActionResultImpl.OK);

    assertEquals(mapping.getFrom(), action0);
    assertEquals(mapping.getTo(), action1);
    assertEquals(mapping.getCondition(), ActionResultImpl.OK);
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestMapping("testConstructor"));

    return suite;
  }

  // Inner action class for testing
  private static class Action0 extends AbstractProcessActionImpl {
    
    public Action0(String id) {
      
      super(id);
    }

    @Override
	public ActionResult exec(ProcessContext ctx) {
      
      ctx.setProperty(getId(), "ok");      
      
      return new Success();
    }
  }
}
