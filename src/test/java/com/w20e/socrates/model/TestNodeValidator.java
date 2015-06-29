package com.w20e.socrates.model;

import com.w20e.socrates.data.XSBoolean;
import com.w20e.socrates.data.XSInteger;
import com.w20e.socrates.expression.Equals;
import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.IfThenElse;
import com.w20e.socrates.expression.Or;
import com.w20e.socrates.expression.RandomInt;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.expression.XVar;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.model.NodeValidator;

import junit.framework.TestCase;

public class TestNodeValidator extends TestCase {

    NodeImpl n;
    ItemPropertiesImpl props;
    ModelImpl model;
    InstanceImpl instance;

    @Override
	public void setUp() {

        this.model = new ModelImpl();
        this.instance = new InstanceImpl();
        this.n = new NodeImpl("/a");
        this.props = new ItemPropertiesImpl("/a");

        this.instance.addNode(this.n);
        this.model.addItemProperties(this.props);

        Equals eq = new Equals();
        eq.setLeftOperand(new XVar("/a"));
        eq.setRightOperand(new XNumber(Integer.valueOf(1)));
        this.props.setRequired(new XBoolean(true));
        this.props.setRelevant(new XBoolean(true));
        this.props.setConstraint(eq);
        this.props.setDatatype(XSInteger.class);
    }

    /*
     * Test method for 'com.w20e.socrates.modelimpl.NodeValidator.getValue(Node,
     * ItemProperties, Model, Instance)'
     */
    public void testGetValue() {

        // this.n.setValue("10");
        // assertEquals(new Integer(10).intValue(),
        // NodeValidator.getValue(this.n, this.props, this.model,
        // this.instance).toNumber().intValue());

        this.props.setDatatype(XSBoolean.class);

        this.n.setValue(Boolean.valueOf(false));

        assertFalse(NodeValidator.getValue(this.n, this.props, this.model,
                this.instance).toBoolean());

        this.n.setValue(Boolean.valueOf(true));

        assertTrue(NodeValidator.getValue(this.n, this.props, this.model,
                this.instance).toBoolean());

        ItemProperties props1 = new ItemPropertiesImpl("/a");

        props1.setCalculate(new XString("yo"));

        assertEquals("yo", NodeValidator.getValue(this.n, props1, this.model,
                this.instance).toString());
        
        assertNull(NodeValidator.getValue(this.n, null, this.model, this.instance).toObject());
    }
    
    public void testGetValueWithDefault() {
        
        ItemProperties props = new ItemPropertiesImpl("/a");

        props.setDefault(new XString("yo"));

        assertEquals(null, n.getValue());
        
        assertEquals("yo", NodeValidator.getValue(this.n, props, this.model, this.instance).toObject());

        assertEquals("yo", n.getValue().toString());

        this.n.setValue("foo");

        assertEquals("foo", n.getValue());
        
        assertEquals("foo", NodeValidator.getValue(this.n, props, this.model, this.instance).toObject());
        
        assertEquals("foo", n.getValue());

    }

    /*
     * Test method for 'com.w20e.socrates.modelimpl.NodeValidator.getValue(Node,
     * ItemProperties, Model, Instance)'
     */
    public void testGetRawValue() {

        this.props.setDatatype(XSBoolean.class);

        this.n.setValue(Boolean.valueOf(false));

        assertFalse((Boolean) NodeValidator.getRawValue(this.n, this.props,
                this.model, this.instance));

        this.n.setValue(Boolean.valueOf(true));

        assertTrue((Boolean) NodeValidator.getRawValue(this.n, this.props,
                this.model, this.instance));

        ItemProperties props1 = new ItemPropertiesImpl("/a");

        props1.setCalculate(new XString("yo"));

        assertEquals("yo", NodeValidator.getRawValue(this.n, props1, this.model,
                this.instance).toString());
    }

    /*
     * Test method for
     * 'com.w20e.socrates.modelimpl.NodeValidator.isReadOnly(Node,
     * ItemProperties, Instance, Model)'
     */
    public void testIsReadOnly() {

        assertFalse(NodeValidator.isReadOnly(this.props, this.instance,
                this.model));
        
        assertNull(NodeValidator.getValue(this.n, null, this.model, this.instance).toObject());
    }

    /*
     * Test method for
     * 'com.w20e.socrates.modelimpl.NodeValidator.isRequired(Node,
     * ItemProperties, Instance, Model)'
     */
    public void testIsRequired() {

        assertTrue(NodeValidator.isRequired(this.props, this.instance,
                this.model));
    }

    /*
     * Test method for
     * 'com.w20e.socrates.modelimpl.NodeValidator.isRelevant(Node,
     * ItemProperties, Instance, Model)'
     */
    public void testIsRelevant() {

        assertTrue(NodeValidator.isRelevant(this.props, this.instance,
                this.model));

        Or or = new Or();

        Equals eq0 = new Equals();
        eq0.setLeftOperand(new XVar("/a"));
        eq0.setRightOperand(new XNumber(Integer.valueOf(1)));

        Equals eq1 = new Equals();
        eq1.setLeftOperand(new XVar("/a"));
        eq1.setRightOperand(new XNumber(Integer.valueOf(3)));

        or.setLeftOperand(eq0);
        or.setRightOperand(eq1);

        this.n.setValue(null);
        this.props.setRelevant(or);

        assertFalse(NodeValidator.isRelevant(this.props, this.instance,
                this.model));

        // Test when node is a boolean type

        this.n.setValue(true);
        this.props.setDatatype(XSBoolean.class);

        this.props.setRelevant(new XVar("/a"));

        assertTrue(NodeValidator.isRelevant(this.props, this.instance,
                this.model));

        this.n.setValue(false);

        assertFalse(NodeValidator.isRelevant(this.props, this.instance,
                this.model));

        this.n.setValue("");

        assertFalse(NodeValidator.isRelevant(this.props, this.instance,
                this.model));

    }

    /*
     * Test method for 'com.w20e.socrates.modelimpl.NodeValidator.validate(Node,
     * ItemProperties, Instance, Model)'
     */
    public void testValidate() {

        try {
            NodeValidator.validate(this.n, this.props, this.instance,
                    this.model);
            fail("Should have failed");
        } catch (Exception e) {
            // As expected.
        }

        this.n.setValue("10");

        try {
            NodeValidator.validate(this.n, this.props, this.instance,
                    this.model);
            fail("Should fail on constraint");
        } catch (Exception e) {
            // As expected.
        }

        this.n.setValue(1);

        try {
            NodeValidator.validate(this.n, this.props, this.instance,
                    this.model);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        Or or = new Or();

        Equals eq0 = new Equals();
        eq0.setLeftOperand(new Undef());
        eq0.setRightOperand(new XNumber(Integer.valueOf(1)));

        Equals eq1 = new Equals();
        eq1.setLeftOperand(new Undef());
        eq1.setRightOperand(new XNumber(Integer.valueOf(3)));

        or.setLeftOperand(eq0);
        or.setRightOperand(eq1);

        this.props.setConstraint(or);

        try {
            NodeValidator.validate(this.n, this.props, this.instance,
                    this.model);
            fail("Should fail on constraint");
        } catch (Exception e) {
            // As expected.
        }

        this.props.setRequired(new XBoolean(false));
        this.props.setConstraint(new XBoolean(true));
        this.n.setValue(null);

        try {
            NodeValidator.validate(this.n, this.props, this.instance,
                    this.model);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    
    /* Test recursion capabilities
     */
    public void testGetValueWithSelf() {
    	
        IfThenElse ite = new IfThenElse();
        RandomInt random = new RandomInt();
        
        XNumber num0 = new XNumber(Long.valueOf(5));

        Expression[] ops = {num0};

        random.setOperands(ops);
        
        ite.setLeftOperand(new XVar("/a"));
        ite.setMiddleOperand(new XVar("/a"));
        ite.setRightOperand(random);
        
        this.props.setCalculate(ite);
        
        assertTrue(NodeValidator.getValue(this.n, this.props, this.model, this.instance).toNumber().intValue() > 0);
        assertTrue(NodeValidator.getValue(this.n, this.props, this.model, this.instance).toNumber().intValue() < 6);
        
        this.n.setValue(3);
        
        assertTrue(NodeValidator.getValue(this.n, this.props, this.model, this.instance).toNumber().intValue() == 3);

    }

}
