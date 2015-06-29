package com.w20e.socrates.util;

import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.rendering.FlowGroup;
import com.w20e.socrates.rendering.Input;
import com.w20e.socrates.rendering.Option;
import com.w20e.socrates.rendering.RenderConfigImpl;
import com.w20e.socrates.rendering.Select;

import junit.framework.TestCase;

public class TestFillProcessor extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testProcessFills() {

		InstanceImpl i = new InstanceImpl();

	    Node node0 = new NodeImpl("/a/b", "0");
	    Node node1 = new NodeImpl("/a/c", "WILLIE");

	    i.addNode(node0);
	    i.addNode(node1);

	    ModelImpl m = new ModelImpl();

	    ItemProperties props0 = new ItemPropertiesImpl("ip0");
		
	    m.addItemProperties(props0);

	    RenderConfigImpl cfg = new RenderConfigImpl();
	    
	    Select s = new Select("s0");
	    s.setBind("/a/b");
	    s.addOption(new Option("0", "YO"));
	    s.addOption(new Option("1", "YA"));

	    Input in = new Input("i0");
	    in.setBind("/a/c");

	    FlowGroup fg = new FlowGroup("fg");
	    fg.addItem(in);
	    
	    cfg.addItem(s);
	    cfg.addItem(fg);
	    
		assertEquals("Let's fill in YO and WILLIE", FillProcessor.processFills("Let's fill in ${s0:ctrl} and ${i0:ctrl}", i, m, cfg, null));
		assertEquals("Let's fill in nothing", FillProcessor.processFills("Let's fill in nothing", i, m, cfg, null));
		assertEquals("Raw value: 0", FillProcessor.processFills("Raw value: ${/a/b:raw}", i, m, cfg, null));
	}

}
