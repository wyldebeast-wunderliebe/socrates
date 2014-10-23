package com.w20e.socrates.rendering;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestRenderOptionsImpl {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {

		RenderOptionsImpl options = new RenderOptionsImpl();

		assertFalse(options.isEnabled("foo", false));
		assertTrue(options.isEnabled("foo", true));
		assertTrue(options.isDisabled("bar", true));
		assertFalse(options.isDisabled("bar", false));

		Map<String, Object> defaults = new HashMap<String, Object>();
		defaults.put("foo", Boolean.TRUE);
		defaults.put("bar", "not boolean");

		options = new RenderOptionsImpl(defaults);

		assertTrue(options.isEnabled("foo", false));
		assertTrue(options.isEnabled("foo", true));

		assertEquals("not boolean", options.get("bar"));
	}

	@Test
	public void testIsEnabled() {
		RenderOptionsImpl options = new RenderOptionsImpl();
		options.disable("foo");
		options.enable("foo");
		assertTrue(options.isEnabled("foo", false));
	}

	@Test
	public void testIsDisabled() {
		RenderOptionsImpl options = new RenderOptionsImpl();
		options.enable("foo");
		options.disable("foo");
		assertTrue(options.isDisabled("foo", false));
	}

}
