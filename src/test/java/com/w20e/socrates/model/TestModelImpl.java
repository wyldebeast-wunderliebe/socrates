/*
 * File      : TestInstanceImpl.java
 * Classname : TestInstanceImpl
 * Author    : Wietze Helmantel
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.23 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import junit.framework.TestCase;

import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.SubmissionImpl;

public class TestModelImpl extends TestCase {

  private ModelImpl model;

  public TestModelImpl( String name ) {
    super(name);
  }

  @Override
public void setUp() {

    // Let's create a rather complex model, so as to test some complex
    // requirements.

    this.model = new ModelImpl();

    ItemProperties props0 = new ItemPropertiesImpl("ip0");
    ItemProperties props1 = new ItemPropertiesImpl("ip1");

    this.model.addItemProperties(props0);
    this.model.addItemProperties(props1);
  }

  public void testSetId() {

    this.model.setId("pipo");
    assertEquals("pipo", this.model.getId());
  }

  public void testSetSubmission() {

    SubmissionImpl sub = new SubmissionImpl();

    this.model.setSubmission(sub);

    assertEquals(this.model.getSubmission(), sub);
  }

  public void testGetAllItemProperties() {
      
      ItemPropertiesImpl ip0 = new ItemPropertiesImpl("ip0");
      ItemPropertiesImpl ip1 = new ItemPropertiesImpl("ip1");

      ip0.addBind("foo");
      ip0.addBind("bar");
      
      ip1.addBind("xxx");
      ip1.addBind("yyy");
      ip1.addBind("zzz");
      
      this.model.addItemProperties(ip0);
      this.model.addItemProperties(ip1);
      
      assertEquals(5, this.model.getAllItemProperties().size());
      assertEquals(ip0, this.model.getItemProperties("foo"));
      assertEquals(ip0, this.model.getItemProperties("foo"));
      assertEquals(ip1, this.model.getItemProperties("xxx"));
      assertEquals(ip1, this.model.getItemProperties("yyy"));
      assertEquals(ip1, this.model.getItemProperties("zzz"));

  }
}
