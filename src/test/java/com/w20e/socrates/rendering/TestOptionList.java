package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestOptionList {

	private OptionList list;
	
	@Before
	public void setUp() throws Exception {
		
		this.list = new OptionList();
	}

	@Test
	public void testSetRefvalue() {

		this.list.setRefvalue("pipo");
		assertEquals("pipo", this.list.getRefValue());
	}

	@Test
	public void testGet() {

		Option opt0 = new Option();
		opt0.setValue("opt0");
		this.list.add("opt", opt0);
		Option opt1 = new Option();
		opt1.setValue("opt1");
		this.list.addOption(opt1);
		
		assertEquals(2, this.list.size());
		assertEquals("opt0", this.list.get("opt").getValue());
		assertEquals("opt1", this.list.get("opt1").getValue());
		assertNull(this.list.get("opt2"));
	}

}
