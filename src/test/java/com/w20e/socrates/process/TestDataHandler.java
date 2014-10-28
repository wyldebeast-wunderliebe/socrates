package com.w20e.socrates.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.w20e.socrates.data.Node;
import com.w20e.socrates.data.XSBoolean;
import com.w20e.socrates.data.XSInteger;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.rendering.ControlImpl;
import com.w20e.socrates.rendering.Input;
import com.w20e.socrates.rendering.RenderStateImpl;
import com.w20e.socrates.rendering.Renderable;

import junit.framework.TestCase;

public class TestDataHandler extends TestCase {

	private InstanceImpl instance = new InstanceImpl();
	private ModelImpl model = new ModelImpl();
	private RenderStateImpl state;
	private HashMap<String, Object> data;
	private ItemProperties props;

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	    // Let's create a model, so as to test some complex
	    // requirements.
	    Node node0 = new NodeImpl("/a");

	    this.instance.addNode(node0);

	    this.props = new ItemPropertiesImpl("/a");

	    this.model.addItemProperties(this.props);
	
	    ControlImpl r0 = new Input("r0");
	    r0.setBind("/a");

	    //ControlImpl r1 = new Select("r1");
	    //r1.setBind("/a");

	    List<Renderable> controls = new ArrayList<Renderable>();
	    
	    controls.add(r0);	    
	    //controls.add(r1);	    
	    
	    this.state = new RenderStateImpl("foo", controls);
	    
	    this.data = new HashMap<String, Object>();
	}
	
		
	// Unit test for integer data
	public void testXSInteger() {
	    
		this.props.setType(XSInteger.class);
		this.data.clear();
		
	    // Let's start with no data: should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Shouldn't have failed here");
		}
		
		// Now throw in erroneous value for integer
		this.data.put("r0", "YOO");

		this.props.setRelevant(new XBoolean(Boolean.FALSE));

	      // This should still not be a problem!
        try {
            DataHandler.setData(this.data, this.model, this.instance, this.state);
        } catch (ValidationException e) {
            fail("Should have failed on invalid type for XSInteger");
        }

        this.props.setRelevant(new XBoolean(Boolean.TRUE));
		
		// This should be a problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
			fail("Should have failed on invalid type for XSInteger");
		} catch (ValidationException e) {
			// As expected.
		}

		// Now throw in proper value for integer
		this.data.put("r0", "12");

		// Should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Should not have failed on valid type for XSInteger");
		}
		
		// Now throw in empty value for integer
		this.data.put("r0", null);

		// If not required, shouldn't be a problem
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Should not have failed on valid type for XSInteger");
		}
				
		// But if it's required...
		this.props.setRequired(new XBoolean(true));
		
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
			fail("Should have failed on empty value for XSInteger");
		} catch (ValidationException e) {
			// No worries...
		}

	}

	
	// Unit test for boolean data
	public void ztestXSBoolean() {
	    
		this.props.setType(XSBoolean.class);
		this.data.clear();
		
	    // Let's start with no data: should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Shouldn't have failed here");
		}
		
		// Now throw in real value
		this.data.put("r0", "12");

		// Should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Should not have failed on valid type for XSBoolean");
		}

		// Now throw in empty value
		this.data.put("r0", "");

		// Should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Shouldn't have failed here");
		}

		// Even if it's required...
		this.props.setRequired(new XBoolean(true));
		
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Shouldn't have failed on empty value for XSBoolean");
		}

	}

	// Unit test for string data
	public void ztestXSString() {
	    
		this.props.setType(XSString.class);
		this.data.clear();
		
	    // Let's start with no data: should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Shouldn't have failed here");
		}
		
		// Now throw in real value
		this.data.put("r0", "12");

		// Should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Should not have failed on valid type for XSString");
		}

		// Now throw in empty value
		this.data.put("r0", "");

		// Should be no problem!
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
		} catch (ValidationException e) {
			fail("Shouldn't have failed here");
		}

		// But if it's required...
		this.props.setRequired(new XBoolean(true));
		
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
			fail("Should have failed on empty value for XSString");
		} catch (ValidationException e) {
			// As expected.
		}

		// and no data at all...
		this.data.clear();
		
	    try {
	    	DataHandler.setData(this.data, this.model, this.instance, this.state);
			fail("Should have failed on undefined value for XSString");
		} catch (ValidationException e) {
			// As expected.
		}
	}
}
