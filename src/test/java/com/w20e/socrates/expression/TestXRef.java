/*
 * File      : TestXRef.java
 * Classname : TestXRef
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.25 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import junit.framework.TestCase;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.XSInteger;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;

public class TestXRef extends TestCase {

  private XRef ref;
  private Instance inst;
  private Model model;
  private ItemProperties props;

  public TestXRef(String name) {
    super(name);
  }

  @Override
public void setUp() {

    this.inst = new InstanceImpl();
    
    ((InstanceImpl) this.inst).addNode(new NodeImpl("foo", "Some value"));
    ((InstanceImpl) this.inst).addNode(new NodeImpl("bar"));

    this.model = new ModelImpl();
    
    this.props = new ItemPropertiesImpl("p0");
    
    try {
      this.ref = new XRef(this.inst.getNode("foo"), this.model, this.inst, this.props);
    } catch (InvalidPathExpression e) {
     fail(e.getMessage());
    }
  }

  public void testEval() {

    assertEquals(this.ref.eval(), this.ref);
    assertEquals(this.ref.eval().toString(), "Some value");

    try {
		this.inst.getNode("/foo").setValue("1.0");
	} catch (InvalidPathExpression e) {
		fail();
	}
    this.props.setDatatype(XSInteger.class);
    assertTrue(this.ref.eval() instanceof XNumber);

    // Let's test the none existing node...
    try {
        this.ref = new XRef(this.inst.getNode("argh"), this.model, this.inst, this.props);
    } catch (InvalidPathExpression e) {
        fail(e.getMessage());
    }
    assertEquals(Undef.UNDEF.toString(), this.ref.eval().toString());
  }

  public void testToNumber() {

    assertEquals(Double.NaN, this.ref.toNumber());

    try {
      this.inst.getNode("foo").setValue("6");
      assertEquals(new Double(6), this.ref.toNumber());

      this.inst.getNode("foo").setValue(null);
      assertEquals(Double.NaN, this.ref.toNumber());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  public void testToString() {

    assertEquals(this.ref.toString(), "Some value");

    try {
      this.inst.getNode("foo").setValue(null);
      assertEquals(Undef.UNDEF.toString(), this.ref.toString());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  public void testToBoolean() {

    assertTrue(this.ref.toBoolean());

    try {
      this.inst.getNode("foo").setValue("");
      assertFalse(this.ref.toBoolean());

      this.inst.getNode("foo").setValue(null);
      assertFalse(this.ref.toBoolean());

    } catch (Exception e) {
      fail(e.getMessage());
    }

    // Let's test the none existing node...
    try {
        this.ref = new XRef(this.inst.getNode("bar"), this.model, this.inst, this.props);
    } catch (InvalidPathExpression e) {
        fail(e.getMessage());
    }
    
    assertFalse(this.ref.toBoolean());

  }

  public void testCompareTo() {

    assertEquals(0, this.ref.compareTo(new XString("Some value")));

    assertTrue(this.ref.compareTo(new XString("Tome value")) < 0);

    assertTrue(this.ref.compareTo(new XString("Rome value")) > 0);
  }

  public void testEquals() {

    assertTrue(this.ref.equals(this.ref));
    assertFalse(this.ref.equals(new XString("cc")));
    assertFalse(this.ref.equals(null));

    try {
      this.inst.getNode("foo").setValue(Integer.valueOf(1));
      assertTrue(this.ref.equals(new XNumber(Integer.valueOf(1))));
      assertTrue(this.ref.equals(new XString("1")));

      this.inst.getNode("foo").setValue("1");
      assertTrue(this.ref.equals(new XNumber(Integer.valueOf(1))));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  public void testToObject() {

      try {
          this.inst.getNode("foo").setValue("1");
      } catch (Exception e) {
          // tough...
      }
      assertEquals("1", this.ref.toObject());
  }

  public void testHashCode() {
      
      try {
          this.inst.getNode("foo").setValue("pipo");
      } catch (Exception e) {
          // tough...
      }
      
      assertEquals("pipo".hashCode(), this.ref.hashCode());
  }

}
