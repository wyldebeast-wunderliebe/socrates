/*
 * File      : TestUndefined.java
 * Classname : TestUndefined
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.3 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import com.w20e.socrates.workflow.ActionResultImpl;
import com.w20e.socrates.workflow.Undefined;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestUndefined extends TestCase {

  private Undefined result;

  public TestUndefined(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.result = new Undefined();
  }

  public void testToString() {

    assertEquals(this.result.toString(), ActionResultImpl.UNDEFINED);
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestUndefined("testToString"));

    return suite;
  }
}
