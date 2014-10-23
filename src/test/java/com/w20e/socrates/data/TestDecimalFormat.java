package com.w20e.socrates.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.expression.XString;

public class TestDecimalFormat {

	private DecimalFormat fmt;
	
	@Before
	public void setUp() throws Exception {
		
		this.fmt = new DecimalFormat();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTransform() {

		assertEquals("10", this.fmt.transform(new XString("10"), null).toString());
		assertEquals(10, this.fmt.transform(new XString("10"), null).toNumber().intValue());
	}

}
