package com.w20e.socrates.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.ActionResultImpl;
import com.w20e.socrates.workflow.Failure;
import com.w20e.socrates.workflow.Mapping;
import com.w20e.socrates.workflow.ProcessAction;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.ProcessContext;
import com.w20e.socrates.workflow.ProcessContextImpl;
import com.w20e.socrates.workflow.ProcessState;
import com.w20e.socrates.workflow.ProcessorImpl;
import com.w20e.socrates.workflow.Success;

public class TestProcessorImpl extends TestCase {

  private ProcessorImpl proc;
  private ProcessAction action0;
  private ProcessContext ctx;

  public TestProcessorImpl(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.action0 = new Action0("a0");

    this.proc = new ProcessorImpl();

    this.ctx = new ProcessContextImpl();

    this.proc.setInitial(this.action0);
  }

  public void testAddMappings() {

    Map<ProcessState, Map<String, ProcessState>> foo =
    	new HashMap<ProcessState, Map<String, ProcessState>>();
    this.proc.addMappings(foo);
    assertEquals(foo, this.proc.getMappings());
  }

  public void testEmpty() {

	  this.proc = new ProcessorImpl();

    try {
    	this.proc.next(this.ctx);
      fail();
    }
    catch (Exception e) {
    	// As expected.
    }
  }

  public void testNext() {

    try {
    	this.proc.next(this.ctx);
    }
    catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    assertEquals(this.ctx.getProperty("a0"), "ok");

    try {
    	this.proc.next(this.ctx);
      fail();
    }
    catch (Exception e) {
    	// As expected.
    }
  }

  public void testHasNext() {

    assertTrue(this.proc.hasNext(this.ctx));

    try {
    	this.proc.next(this.ctx);
    }
    catch (Exception e) {
      fail();
    }

    assertFalse(this.proc.hasNext(this.ctx));
  }

  public void testAddMapping() {

    ProcessAction action1 = new Action0("a1");
    ProcessAction action2 = new Action2("a2");
    
    ProcessorImpl subProc = new ProcessorImpl();
    subProc.setInitial(new Action0("initSub"));

    this.proc.addMapping(new Mapping(this.action0, action1, ActionResultImpl.FAIL));
    this.proc.addMapping(new Mapping(this.action0, action2, ActionResultImpl.OK));
    this.proc.addMapping(new Mapping(action2, action1, ActionResultImpl.FAIL));
    this.proc.addMapping(new Mapping(action2, subProc, ActionResultImpl.OK));

    try {
    	this.proc.next(this.ctx);

      assertEquals(this.ctx.getProperty("a0"), "ok");

      this.proc.next(this.ctx);

      assertNull(this.ctx.getProperty("a1"));
      assertEquals(this.ctx.getProperty("a2"), "fail");

      this.proc.next(this.ctx);

      assertEquals(this.ctx.getProperty("a1"), "ok");
    }
    catch (Exception e) {
      fail();
    }

    try {
    	this.proc.next(this.ctx);
      fail();
    }
    catch (Exception e) {
    	// As expected.
    }
    
    ProcessorImpl p1 = new ProcessorImpl();
    p1.addMapping(new Mapping(this.action0, action2, ActionResultImpl.OK));
    List<ProcessAction> finals = new ArrayList<ProcessAction>();
    finals.add(action2);
    p1.setFinals(finals);
    
    ProcessorImpl p2 = new ProcessorImpl();
    p2.addMapping(new Mapping(action2, action2, ActionResultImpl.OK));

    this.proc.addMapping(new Mapping(p1, action0, ActionResultImpl.OK));
    this.proc.addMapping(new Mapping(p2, action2, ActionResultImpl.OK));
    
    // Now we should have a mapping from p1.action2 to action 
    // @TODO implement tests for mappings.
    
  }

  public void testIsFinished() {

    ProcessAction action1 = new Action0("a1");

    this.proc.addMapping(new Mapping(this.action0, action1, ActionResultImpl.OK));

    List<ProcessAction> finals = new ArrayList<ProcessAction>();

    finals.add(action1);

    this.proc.setFinals(finals);
    
    assertFalse(this.proc.isFinished(this.ctx));

    try {
    	this.proc.next(this.ctx);
    	this.proc.next(this.ctx);
    }
    catch (Exception e) {
      fail();
    }

    assertTrue(this.proc.isFinished(this.ctx));
  }

  public void testSetMappings() {
      
      Map<ProcessState, Map<String, ProcessState>> map = new HashMap<ProcessState, Map<String, ProcessState>>();
      this.proc.setMappings(map);
      
      assertEquals(map, this.proc.getMappings());
  }
  
  // Inner action class for testing
  private static class Action0 extends AbstractProcessActionImpl {
    
    public Action0(String id) {
      
      super(id);
    }
    
    @Override
	public ActionResult exec(ProcessContext context) {
      
      context.setProperty(getId(), "ok");
      
      return new Success();
    }
  }

  // Failing action
  private static class Action2 extends AbstractProcessActionImpl {
    
    public Action2(String id) {
      
      super(id);
    }
    
    @Override
	public ActionResult exec(ProcessContext context) {
      
      context.setProperty(getId(), "fail");   
      
      return new Failure();
    }
  }
}
