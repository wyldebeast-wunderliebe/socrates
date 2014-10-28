/*
 * File      : TestSubmissionImpl.java
 * Classname : TestSubmissionImpl
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.4 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.net.URI;

import com.w20e.socrates.model.SubmissionImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSubmissionImpl extends TestCase {

  private SubmissionImpl sub;

  public TestSubmissionImpl(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.sub = new SubmissionImpl();
  }

  public void testSetId() {

    this.sub.setId("xxx");

    assertEquals(this.sub.getId(), "xxx");
  }

  public void testSetMethod() {

    this.sub.setMethod("text/plain");

    assertEquals(this.sub.getMethod(), "text/plain");
  }

  public void testSetAction() {

    try {
      this.sub.setAction(new URI("file:///file.xml"));

      assertEquals(this.sub.getAction().toString(), "file:///file.xml");
    } catch (Exception e) {
      fail();
    }
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new TestSubmissionImpl("testSetMethod"));
    suite.addTest(new TestSubmissionImpl("testSetId"));
    suite.addTest(new TestSubmissionImpl("testSetAction"));

    return suite;
  }
}
