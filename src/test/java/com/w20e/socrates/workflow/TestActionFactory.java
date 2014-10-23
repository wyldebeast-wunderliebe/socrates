/*
 * File      : TestActionFactory.java
 * Classname : TestActionFactory
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.4 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.apache.xalan.xsltc.runtime.AttributeList;

import com.w20e.socrates.workflow.ActionFactory;
import com.w20e.socrates.workflow.ProcessAction;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;

public class TestActionFactory {

    private ActionFactory fac;
    private AttributeList attr;

    @Before
    public void setUp() {

        this.fac = new ActionFactory();
        this.attr = new AttributeList();
    }

    @Test
    public void testCreateObject() {

        Object obj = null;

        try {
            obj = this.fac.createObject(this.attr);
            fail();
        } catch (Exception e) {
            // As expected.
        }

        this.attr.add("id", "");
        this.attr.add("class", "");

        try {
            obj = this.fac.createObject(this.attr);
            fail();
        } catch (Exception e) {
            // As expected.
        }

        this.attr.clear();

        this.attr.add("id", "foo");
        this.attr.add("class", "com.w20e.socrates.process.Render");

        try {
            obj = this.fac.createObject(this.attr);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(obj instanceof ProcessAction);
        assertTrue(obj instanceof AbstractProcessActionImpl);

        assertEquals(((ProcessAction) obj).getId(), "foo");
        assertEquals(obj.getClass().getName(), this.attr.getValue("class"));
    }

    @Test
    public void testPutAction() {

        ProcessAction action = new TestAction("foo");
        ActionFactory.putAction("foo", action);
        assertEquals(action, ActionFactory.getAction("foo"));
    }

    private static class TestAction extends AbstractProcessActionImpl {

        public TestAction(String actionId) {
            super(actionId);
        }

        @Override
        public ActionResult exec(ProcessContext context)
                throws ActionExecException {
            return null;
        }

    }
}
