package com.w20e.socrates.model.util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.NodeImpl;

public class TestInstanceXMLSerializer {

    InstanceXMLSerializer ser;
    
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDeserialize() {

        try {
            Instance inst = InstanceXMLSerializer.deserialize(new URI("file:./target/test-classes/test-instance.xml"));

            assertEquals("pipo", inst.getMetaData().get("lala"));
            
            assertEquals("1", inst.getNode("/foo").getValue().toString());
            assertEquals("xxxx", inst.getNode("/bar").getValue().toString());
        } catch (URISyntaxException e) {
            fail(e.toString());
        } catch (InvalidPathExpression e) {
            fail(e.toString());
        }

        try {
            Instance inst = InstanceXMLSerializer.deserialize(new URI("file:./target/test-classes/test-broken-instance.bxml"));

            assertNotNull(inst.getMetaData().get("error"));
            
            System.out.println(inst.getMetaData().get("error"));
            
        } catch (URISyntaxException e) {
            fail(e.toString());
        }
        
        try {
            Instance inst = InstanceXMLSerializer.deserialize(new URI("file:./target/test-classes/test-nonexistent-instance.xml"));

            assertNotNull(inst.getMetaData().get("error"));
            
            System.out.println(inst.getMetaData().get("error"));
            
        } catch (URISyntaxException e) {
            fail(e.toString());
        }

    }

    
    @Test
    public void testSerialize() {

        // Let's make a round trip...
        
        InstanceImpl inst = new InstanceImpl();

        Date now = new Date();

        Node n0 = new NodeImpl("foo0", "bar");
        Node n1 = new NodeImpl("foo1", Integer.valueOf(10));
        Node n2 = new NodeImpl("foo2", now);
        Node n3 = new NodeImpl("foo3", null);
        
        inst.addNode(n0);
        inst.addNode(n1);
        inst.addNode(n2);
        inst.addNode(n3);
        
        try {
            File file = new File("./target/test-classes/serialized-instance.xml");
            
            InstanceXMLSerializer.serialize(inst, file);
            
            assertTrue(file.exists());

            Instance inst2 = InstanceXMLSerializer.deserialize(new URI("file:./target/test-classes/serialized-instance.xml"));
            
            assertEquals("bar", inst2.getNode("foo0").getValue());
            assertEquals("10", inst2.getNode("foo1").getValue());
            assertNull(inst2.getNode("foo3").getValue());
            
        } catch (Exception e) {
            fail(e.toString());
        }
    }

}
