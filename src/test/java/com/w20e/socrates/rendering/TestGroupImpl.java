/*
 * Created on Feb 18, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.rendering;

import junit.framework.TestCase;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestGroupImpl extends TestCase {

  private GroupImpl group;
  
  public TestGroupImpl(String name) {
    super(name);
  }
  
  /*
   * @see TestCase#setUp()
   */
  @Override
protected void setUp() throws Exception {

    super.setUp();
    this.group = new FlowGroup("fg");

    this.group.addItem(new Input("i0"));
    this.group.addItem(new Input("i1"));
  }

  public void testGetItems() {
    
    assertEquals(2, this.group.getItems().size());
    assertEquals("i0", ((Control) this.group.getItems().toArray()[0]).getId());
    assertEquals("i1", ((Control) this.group.getItems().toArray()[1]).getId());
  }

  public void testGetItem() {
	  assertEquals("i0", ((Control) this.group.getItem(0)).getId());
  }
 
  public void testGetHint() {
      this.group.setHint(new TranslatableImpl("HINT"));
      assertEquals("HINT", this.group.getHint());
  }
}
