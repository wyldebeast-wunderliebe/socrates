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
public class TestFlowGroup extends TestCase {

  private FlowGroup group;
  
  public TestFlowGroup(String name) {
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

  public void testSetId() {

    this.group.setLabel(new TranslatableImpl("Label"));
    assertEquals("Label", this.group.getLabel().toString());
  }

  public void testGetItems() {
    
    assertEquals(2, this.group.getItems().size());
    assertEquals("i0", ((Control) this.group.getItems().toArray()[0]).getId());
    assertEquals("i1", ((Control) this.group.getItems().toArray()[1]).getId());
  }

  public void testGetOrientation() {
      assertEquals("vertical", this.group.getOrientation());
      this.group.setOrientation("argh");
      assertEquals("vertical", this.group.getOrientation());
      this.group.setOrientation("horizontal");
      assertEquals("horizontal", this.group.getOrientation());
      this.group.setOrientation("vertical");
      assertEquals("vertical", this.group.getOrientation());
  }
}
