/*
 * File      : TestXRefSolver.java
 * Classname : TestXRefSolver
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.22 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.util.ArrayList;

import org.junit.Test;

import com.w20e.socrates.data.Node;
import com.w20e.socrates.expression.And;
import com.w20e.socrates.expression.Equals;
import com.w20e.socrates.expression.Eval;
import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.IfThenElse;
import com.w20e.socrates.expression.Not;
import com.w20e.socrates.expression.NotEquals;
import com.w20e.socrates.expression.Or;
import com.w20e.socrates.expression.Sum;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.expression.XRef;
import com.w20e.socrates.expression.XVar;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.model.XRefSolver;

import static org.junit.Assert.*;


public class TestXRefSolver {

    @Test
    public void testConstructor() {

        try {
            XRefSolver.class.newInstance();
            fail("Should not be possible to instantiate XRefSolver");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    public void testResolve() {

        InstanceImpl instance0 = new InstanceImpl(new ArrayList<Node>());
        InstanceImpl instance1 = new InstanceImpl(new ArrayList<Node>());
        InstanceImpl instance2 = new InstanceImpl(new ArrayList<Node>());

        Model model = new ModelImpl();

        ItemProperties props = new ItemPropertiesImpl("p0");
        ((ModelImpl) model).addItemProperties(props);

        NodeImpl node0 = new NodeImpl("/foo", "mamaloe");
        instance0.addNode(node0);

        NodeImpl node1 = new NodeImpl("/foo", "klukkluk");
        instance1.addNode(node1);

        Expression expr0 = new Equals();
        Expression expr = null;

        ((Equals) expr0).setLeftOperand(new XString("pipo"));
        ((Equals) expr0).setRightOperand(new XVar("/foo"));

        expr = XRefSolver.resolve(model, instance0, expr0, node1);

        assertTrue(((Equals) expr).getRightOperand() instanceof XRef);

        assertFalse(expr.toBoolean());

        node0.setValue("pipo");
        assertTrue(expr.toBoolean());

        assertFalse(XRefSolver.resolve(model, instance1, expr0, node1).toBoolean());

        assertFalse(XRefSolver.resolve(model, instance1, expr0, node1).toBoolean());

        ((Equals) expr).setLeftOperand(new XNumber(Integer.valueOf(4)));

        node0.setValue(Integer.valueOf(2));

        Sum op = new Sum();

        op.setOperands(new Expression[] { new XNumber(Integer.valueOf(2)),
                new XVar("/foo") });

        ((Equals) expr).setRightOperand(op);

        expr = XRefSolver.resolve(model, instance0, expr, node1);

        assertEquals(expr.toString(), "4 == sum(2,2,)");

        assertTrue(expr.toBoolean());

        node0.setValue(Integer.valueOf(1));

        assertFalse(expr.toBoolean());

        // Try Unary operator
        Not not = new Not();
        not.setLeftOperand(new XVar("/foo"));

        expr = XRefSolver.resolve(model, instance0, not, node1);

        assertEquals(expr.toString(), "not(1)");

        assertFalse(expr.toBoolean());

        node0.setValue("");

        assertEquals(expr.toString(), "not()");
        assertTrue(expr.toBoolean());

        // Let's see what happens when node has no value.
        node1.setValue(null);

        ((Equals) expr0).setRightOperand(new XNumber(Integer.valueOf(2)));
        ((Equals) expr0).setLeftOperand(new XVar("/foo"));

        expr = XRefSolver.resolve(model, instance1, expr0, node1);

        assertFalse(expr.toBoolean());

        // Now try faulty node
        expr = XRefSolver.resolve(model, instance0, new XVar("/klukkluk"), node1);

        assertTrue(expr instanceof Undef);

        // More complex case
        node0 = new NodeImpl("/a", "10");
        node1 = new NodeImpl("/b");

        instance2.addNode(node0);
        instance2.addNode(node1);

        NotEquals ne0 = new NotEquals();
        ne0.setLeftOperand(new XVar("/a"));
        ne0.setRightOperand(new XNumber(Integer.valueOf(10)));

        NotEquals ne1 = new NotEquals();
        ne1.setLeftOperand(new XVar("/a"));
        ne1.setRightOperand(new XNumber(Integer.valueOf(18)));

        And and = new And();
        and.setLeftOperand(ne0);
        and.setRightOperand(ne1);

        Or or = new Or();
        or.setLeftOperand(new XVar("/b"));
        or.setRightOperand(and);

        expr = XRefSolver.resolve(model, instance2, ne0, node1);

        assertFalse(expr.toBoolean());

        node0.setValue("");

        expr = XRefSolver.resolve(model, instance2, new XVar("/a"), node1);
  
        assertFalse(expr.toBoolean());

        // Let's see whether the Eval statement works...
        Eval eval = new Eval("10 * 5");

        expr = XRefSolver.resolve(model, instance0, eval, node1);
    
        assertEquals("50.0", expr.eval().toString());

        // And now, let's try one with a ref...
        node0.setValue(Integer.valueOf(10));

        eval = new Eval("3 * /a");

        expr = XRefSolver.resolve(model, instance2, eval, node1);
        
        assertEquals("30.0", expr.eval().toString());

        // Now let's make /a an expression by itself...
        node0.setValue("4 * 8");

        eval = new Eval("/a");
        
        expr = XRefSolver.resolve(model, instance2, eval, node1);
        
        assertEquals("32.0", expr.eval().toString());

        node1.setValue(Integer.valueOf(3));
        node0.setValue("/b * 6");

        eval = new Eval("/a");

        expr = XRefSolver.resolve(model, instance2, eval, node1);
        
        assertEquals("18.0", expr.eval().toString());

        node0.setValue(Integer.valueOf(10));

        // Did we do functions yet..?
        Sum sum = new Sum();
        Expression[] ops = { new XVar("/a"), new XVar("/a"), new XVar("/a"),
                new XVar("/a") };
        sum.setOperands(ops);

        expr = XRefSolver.resolve(model, instance2, sum, node1);
        
        assertEquals("40.0", expr.eval().toString());
        
        // Test ternary epxression
        IfThenElse ite = new IfThenElse();
        ite.setLeftOperand(new XBoolean(true));
        ite.setMiddleOperand(new XString("ok"));
        ite.setRightOperand(new XString("argh"));
        
        expr = XRefSolver.resolve(model, instance2, ite, node1);
        
        assertEquals("ok", expr.eval().toString());
    }
}
