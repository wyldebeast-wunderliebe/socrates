package com.w20e.socrates.rendering;

import junit.framework.TestCase;

public class TestRenderableContainerImpl extends TestCase {

	private RenderableContainerImpl container;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		this.container = new RenderableContainerImpl();
	}

	public void testGetItems() {

		assertEquals(0, this.container.getItems().size());

		this.container.addItem(new Input("i0"));
		this.container.addItem(new Input("i1"));
		
		assertEquals(2, this.container.getItems().size());		
	}

	public void testGetItem() {

		Input i0 = new Input("i0");
		Input i1 = new Input("i1");
		Input i2 = new Input("i2");
		Input i3 = new Input("i3");
		FlowGroup f0 = new FlowGroup("f0");
		FlowGroup f1 = new FlowGroup("f1");
		FlowGroup f2 = new FlowGroup("f2");

		f1.addItem(i3);
		f1.addItem(i2);

		f0.addItem(f1);
		f0.addItem(f2);
		
		this.container.addItem(i0);
		this.container.addItem(i1);
		this.container.addItem(f0);
		
		assertEquals(i0, this.container.getItem("i0"));	
		assertEquals(i1, this.container.getItem("i1"));	
		assertEquals(i2, this.container.getItem("i2"));
		assertEquals(i3, this.container.getItem("i3"));
		assertNull(this.container.getItem("xxx"));
	}

}
