/*
 * File      : TestRunnerContext.java
 * Classname : TestRunnerContext
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.2 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.OutputStream;

import com.w20e.socrates.process.RunnerContextImpl;

public class TestRunnerContext extends TestCase {

  private RunnerContextImpl ctx;

  public TestRunnerContext(String name) {
    super(name);
  }

  public void setUp() {

    this.ctx = new RunnerContextImpl(null, null, null, null, null, null);
  }

  public void testSetOutputStream() {

    OutputStream out = System.out;

    this.ctx.setOutputStream(out);

    assertEquals(out, this.ctx.getOutputStream());
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestRunnerContext("testSetOutputStream"));

    return suite;
  }
}
