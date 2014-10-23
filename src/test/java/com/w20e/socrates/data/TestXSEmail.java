package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestXSEmail {

	private XSEmail email;
	
	@Before
	public void setUp() throws Exception {
		
		this.email = new XSEmail();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {

		try {
			assertEquals("foo@bar.com", this.email.eval("foo@bar.com").toString());
		} catch (Exception e) {
			fail("Couldn't eval email");
		}
	}

}
