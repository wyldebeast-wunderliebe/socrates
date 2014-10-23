package com.w20e.socrates.data;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class TestToList extends TestCase {

	ToList tl;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		this.tl = new ToList(); 
	}

	public void testTransform() {

		ArrayList<Object> list = new ArrayList<Object>();
		
		try {
			assertEquals(this.tl.transform(list), list);
		} catch (TransformationException e) {
			fail("Exception in transform");
		}
		
		String str = "foo";
		
		try {
			assertTrue(this.tl.transform(str) instanceof List);
			
			assertEquals(((List) this.tl.transform(str)).get(0), str);
		} catch (TransformationException e) {
			fail("Exception in transform");
		}
		
	}
}
