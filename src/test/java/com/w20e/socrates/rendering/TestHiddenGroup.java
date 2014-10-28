package com.w20e.socrates.rendering;

import junit.framework.TestCase;

public class TestHiddenGroup extends TestCase {

	private HiddenGroup grp;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		this.grp = new HiddenGroup("hidden");
	}

	public void testGetType() {
		assertEquals("hiddengroup", this.grp.getType());
	}
}
