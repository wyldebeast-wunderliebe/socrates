/*
 * File      : TestProcessorFactory.java
 * Classname : TestProcessorFactory
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.10 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.net.URL;

import junit.framework.TestCase;

import com.w20e.socrates.workflow.ProcessAction;
import com.w20e.socrates.workflow.Processor;
import com.w20e.socrates.workflow.ProcessorFactory;
import com.w20e.socrates.workflow.ProcessorImpl;

public class TestProcessorFactory extends TestCase {

  private ProcessorFactory fac;

  public TestProcessorFactory(String name) {
    super(name);
  }

  public void setUp() {

    this.fac = new ProcessorFactory(null);
  }

  public void testCreateProcessorFromFile() {

    try {
      Processor proc = this.fac.createProcessor(new URL("file:./target/test-classes/workflow.xml"));

      assertEquals(proc.getInitial().getId(), "a0");
      assertEquals(proc.getInitial().getProperty("foo"), "bar");

      assertEquals(proc.getFinals().size(), 1);

      assertEquals(((ProcessAction) proc.getFinals().get(0) ).getId(), "a1");
      
          
    } catch (Exception e) {
      fail();
    }
  }

  public void testCreateProcessorFromURI() {

    try {
      Processor proc = 
        this.fac.createProcessor(new URL("file:./target/test-classes/workflow.xml"));

      assertEquals(proc.getInitial().getId(), "a0");
      assertEquals(proc.getFinals().size(), 1);

      assertEquals(((ProcessAction) proc.getFinals().get(0) ).getId(), "a1");
    } catch (Exception e) {
      fail();
    }
  }

  public void testCreateProcessorWithProcessor() {

    try {
      ProcessorImpl proc = new ProcessorImpl();

      this.fac = new ProcessorFactory(proc);
      
      this.fac.createProcessor(new URL("file:./target/test-classes/workflow.xml"));

      assertEquals(proc.getInitial().getId(), "a0");
      assertEquals(proc.getFinals().size(), 1);

      assertEquals(((ProcessAction) proc.getFinals().get(0) ).getId(), "a1");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}
