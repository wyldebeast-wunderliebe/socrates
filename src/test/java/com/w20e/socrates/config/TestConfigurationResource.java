package com.w20e.socrates.config;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Before;
import org.junit.Test;

public class TestConfigurationResource {

	ConfigurationResource resource;

	@Before
	public void setUp() throws Exception {

		this.resource = ConfigurationResource.getInstance();
	}

	@Test
	public void testGetInstance() {

		ConfigurationResource resource2 = ConfigurationResource.getInstance();

		assertEquals(this.resource, resource2);
	}

	@Test
	public void testGetConfiguration() {

		try {
			Configuration cfg = this.resource.getConfiguration();
			assertEquals("fallback", cfg.getString("foo"));
		} catch (ConfigurationException e) {
			fail("Couldn't get configuration");
		}

		System.setProperty("socrates.config.url",
				"file:./target/test-classes/socrates-test-config.xml");

		try {
			Configuration cfg = this.resource.getConfiguration();
			assertEquals("bar", cfg.getString("foo"));
		} catch (ConfigurationException e) {
			fail("Couldn't get configuration");
		}

		try {
			// Let's modify the configuration file...
			File config = new File("./target/test-classes/include-config.xml");
			BufferedReader reader = new BufferedReader(new FileReader(config));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			StringBuilder orig = new StringBuilder();
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				orig.append(line);
				orig.append(ls);
				line = line.replaceAll("bar", "bar2");
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			reader.close();

			PrintWriter writer = new PrintWriter(config);
			writer.print(stringBuilder.toString());
			writer.close();

			Configuration cfg = this.resource.getConfiguration();
			assertEquals("bar2", cfg.getString("foo"));

			writer = new PrintWriter(config);
			writer.print(orig.toString());
			writer.close();
			
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}

		System.setProperty("socrates.config.url",
				"lala:./target/test-classes/socrates-test-config.xml");

		try {
			this.resource.getConfiguration();
			fail("Shouldn't get configuration");
		} catch (ConfigurationException e) {
		}

	}

	@Test
	public void testGetConfigurationURL() {

		try {
			Configuration cfg = this.resource.getConfiguration(new URL(
					"file:./target/test-classes/socrates-test-config.xml"));
			assertEquals("bar", cfg.getString("foo"));
		} catch (ConfigurationException e) {
			fail("Couldn't get configuration");
		} catch (MalformedURLException e) {
			fail("Malformed URL");
		}

	}

}
