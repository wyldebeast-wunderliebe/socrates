package com.w20e.socrates.rendering;

import junit.framework.TestCase;

public class TestGridGroup extends TestCase {

	private GridGroup grp;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		this.grp = new GridGroup("grittie");
	}

	public void testGetCols() {

		assertEquals(2, this.grp.getCols());
		
		this.grp.setCols(5);

		assertEquals(5, this.grp.getCols());
	}

	public void testGetType() {
		assertEquals("gridgroup", this.grp.getType());
	}
}
