/*
 * File      : TestInstanceImpl.java
 * Classname : TestInstanceImpl
 * Author    : Wietze Helmantel
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.w20e.socrates.data.Node;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.NodeImpl;

public class TestInstanceImpl extends TestCase {

    private InstanceImpl instance;

    public TestInstanceImpl(String name) {
        super(name);
    }

    public void setUp() {

        // prepare the testset

        Node a = new NodeImpl("/a", "");
        Node b = new NodeImpl("/a/b", "");
        Node c1 = new NodeImpl("/a/b/c1", "");
        Node c2 = new NodeImpl("/a/b/c2", "");

        ArrayList<Node> nodes = new ArrayList<Node>(4);
        nodes.add(a);
        nodes.add(b);
        nodes.add(c1);
        nodes.add(c2);

        this.instance = new InstanceImpl(nodes);
    }

    public void testConstructor() {

        try {
            Instance otherInstance = new InstanceImpl(null);
            assertEquals(0, otherInstance.getNodes("/*").size());
        } catch (InvalidPathExpression invalidPathExpression) {
            fail("pathExpr used in unittest incorrectly formatted.");
        }
    }

    public void testGetNodes() {

        try {
            assertEquals(4, this.instance.getNodes("/*").size());
            assertEquals(3, this.instance.getNodes("/a/*/").size());
            assertEquals(3, this.instance.getNodes("/a/*").size());
            assertEquals(2, this.instance.getNodes("/a/b/*").size());
            assertEquals(1, this.instance.getNodes("/a/b").size());

            Node x = new NodeImpl("/a/b", "");
            Node y = new NodeImpl("/a/b", "");

            ArrayList<Node> nodes = new ArrayList<Node>(4);
            nodes.add(x);
            nodes.add(y);

            new InstanceImpl(nodes);

            /*
             * What to do with this test? try{ instance.getNodes("/a/b");
             * fail("A DuplicateNodeException was expected."); } catch
             * (DuplicateNodeException e) { // no worries }
             */
        } catch (InvalidPathExpression invalidPathExpression) {
            fail("pathExpr used in unittest incorrectly formatted.");
        }
    }

    public void testGetNode() {

        Node node;

        try {
            node = this.instance.getNode("/a/b");
            assertNotNull(node);
            node = this.instance.getNode("/e");
            assertNull(node);
        } catch (InvalidPathExpression invalidPathExpression) {
            fail("pathExpr used in unittest incorrectly formatted.");
        }
    }

    public void testAddNode() {

        this.instance.addNode(new NodeImpl("/a/b/c3", ""));

        // add a node without a root
        try {
            this.instance.addNode(new NodeImpl("foo", ""));
            assertNotNull(this.instance.getNode("/foo"));
        } catch (Exception e) {
            fail("Couldn't add node.");
        }
    }

    public void testSerialize() {

        String filename = "./target/instance.ser";
        InstanceImpl restoredInst = null;

        // do the serialization...
        try {
            this.instance.getNode("/a").setValue("Mamaloe");
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this.instance);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fin);
            restoredInst = (InstanceImpl) in.readObject();
            in.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertNotNull(restoredInst);

        try {
            assertEquals("Mamaloe", restoredInst.getNode("/a").getValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            new File(filename).delete();
        } catch (Exception e) {
            // so what?
        }
    }

    public void testGetId() {
        this.instance.setId("ID");
        assertEquals("ID", this.instance.getId());
    }
}
