package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.data.ToBoolean;
import com.w20e.socrates.expression.XBoolean;

import junit.framework.TestCase;

public class TestToBoolean extends TestCase {

  /*
   * Test method for 'com.w20e.socrates.dataimpl.ToBoolean.transform(Object)'
   */
  public void testTransform() {

    ToBoolean transformer = new ToBoolean();

    assertTrue(((Boolean) transformer.transform("1") ).booleanValue());
    assertFalse(((Boolean) transformer.transform(null) ).booleanValue());
    assertFalse(((Boolean) transformer.transform("") ).booleanValue());
    assertFalse(((Boolean) transformer.transform("FaLse") ).booleanValue());
    assertTrue(((Boolean) transformer.transform("1x") ).booleanValue());
    assertTrue(((Boolean) transformer.transform("trUe") ).booleanValue());
    assertTrue(((Boolean) transformer.transform(Integer.valueOf(1)) ).booleanValue());
    assertFalse(((Boolean) transformer.transform(Integer.valueOf(0)) ).booleanValue());
    assertFalse(((Boolean) transformer.transform(new XBoolean(false)) ).booleanValue());    
    assertFalse(((Boolean) transformer.transform(Integer.valueOf(0), new Locale("nl_NL")) ).booleanValue());
  }

}
