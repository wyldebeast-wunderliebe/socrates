package com.w20e.socrates.process;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URI;

import junit.framework.Assert;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.config.ConfigurationResource;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.StateManager;

public class TestDefaultStateManager {

	StateManager sm;
	
	@Before
	public void setUp() throws Exception {
		
	    File file = new File("./target/test-classes/socrates-test-config.xml");
	    Configuration cfg = ConfigurationResource.getInstance()
	    .getConfiguration(file.toURI().toURL());
	    
	    Model model = ModelResource.getInstance().getModel(
	        new URI("file:./target/test-classes/survey-model.xml"), cfg
	        );

	    this.sm = new DefaultStateManager();
	    
	    this.sm.init(cfg, ModelResource.getInstance()
	        .getRenderConfig(new URI("file:./target/test-classes/survey-model.xml"), cfg),
	        model,
	        ModelResource.getInstance().createInstance(
	            new URI("file:./target/test-classes/survey-model.xml"), cfg)
	    );
		
	}

	@Test
	public void testNext() {

	    RenderState state = this.sm.next();
	}

	@Test
	public void testPrevious() {

	}

}
