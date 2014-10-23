/*
 * File      : TestStateImpl.java
 * Classname : TestStateImpl
 * Author    : Duco Dokter
 * Created   : Thu Feb  3 13:47:55 2005
 * Version   : $Revision: 1.6 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.rendering;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.w20e.socrates.data.Node;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.rendering.RenderStateImpl;

public class TestRenderState extends TestCase {

  private RenderStateImpl state;
  private ModelImpl model;
  private InstanceImpl instance;

  public TestRenderState( String name ) {
    super(name);
  }

  public void setUp() {

    this.instance = new InstanceImpl();

    Node node0 = new NodeImpl("/a/b", this.instance);
    Node node1 = new NodeImpl("/a/c", this.instance);
    Node node2 = new NodeImpl("/b/d", this.instance);
    Node node3 = new NodeImpl("/b/e", this.instance);

    this.instance.addNode(node0);
    this.instance.addNode(node1);
    this.instance.addNode(node2);
    this.instance.addNode(node3);

    this.model = new ModelImpl();

    ItemProperties props0 = new ItemPropertiesImpl("ip0");

    props0.setRelevant(new XBoolean(true));

    this.model.addItemProperties(props0);

    ItemProperties props1 = new ItemPropertiesImpl("ip1");

    props1.setRelevant(new XBoolean(false));

    this.model.addItemProperties(props1);

    ArrayList<Renderable> list = new ArrayList<Renderable>();
    this.state = new RenderStateImpl("foo", list);

  }

  public void testConstructor() {
 
    ArrayList<Renderable> list = new ArrayList<Renderable>();
    RenderState state1 = new RenderStateImpl("foo", list);
    
    assertEquals(list, state1.getItems());

    state1 = new RenderStateImpl("foo");

    assertTrue(state1.getItems().isEmpty());
  }
 
  public void testAddItem() {
   
      assertEquals(0, this.state.getItems().size());

      this.state.addItem(new Input("#ID0#"));
      this.state.addItem(new Input("#ID1#"));
      
      assertEquals(2, this.state.getItems().size());
  }
  
  public void testGetId() {
      this.state.setId("#ID#");
      assertEquals("#ID#", this.state.getId());
  }
}
