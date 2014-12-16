/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.submission;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import junit.framework.TestCase;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.model.Submission;
import com.w20e.socrates.model.SubmissionImpl;
import com.w20e.socrates.model.util.InstanceXMLSerializer;

/**
 * @author dokter
 * Test submission handler.
 */
public class TestXMLFileSubmissionHandler extends TestCase {

  private SubmissionHandler handler;
  private String filename;

  public TestXMLFileSubmissionHandler(String name) {
    super(name);
  }

  /*
   * @see TestCase#setUp()
   */
  @Override
protected void setUp() throws Exception {
    super.setUp();

    this.handler = new XMLFileSubmissionHandler();
  }


  public void testSubmit() {

    SimpleDateFormat formatter =
      new SimpleDateFormat("yyyyMMddHHmmss");

    InstanceImpl instance = new InstanceImpl();
    ModelImpl model = new ModelImpl();
    
    instance.addNode(new NodeImpl("/foo/foo2/foo3/", "bar & boos; <are we escaped yet?>"));
    instance.addNode(new NodeImpl("/bar"));
    instance.addNode(new NodeImpl("/bar2"));

    ItemPropertiesImpl props = new ItemPropertiesImpl("p", "/bar");
    
    props.setCalculate(new XString("argh"));    
        
    model.addItemProperties(props);
    
    Map<String, Object> meta = instance.getMetaData();

    meta.put("qId", "q1");
    meta.put("locale", "nl_NL");
    meta.put("key", "0123456789");
    meta.put("filename", meta.get("qId")
            + "_"
            + meta.get("locale")
            + "_" + formatter.format(Calendar.getInstance().getTime())
            + "_" + meta.get("key")
        );
    meta.put("pipo", null);

    Submission sub = new SubmissionImpl();
    try {
		sub.setAction(new URI("file:///./target"));
	} catch (URISyntaxException e1) {
		fail("Exeption in URI creation");
	}

    try {
      this.handler.submit(instance, model, sub);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    this.filename =
      "./target/q1_nl_NL_"
      + formatter.format(instance.getMetaData().get("created"))
      + "_0123456789.xml";

    File file = new File(this.filename);

    assertTrue(file.exists());
    
    Instance inst2;
    
    try {
        inst2 = InstanceXMLSerializer.deserialize(new URI("file:" + this.filename));
        
        assertEquals("argh", inst2.getNode("/bar").getValue().toString());
        
    } catch (URISyntaxException e) {
        fail("Exception in deserialize file instance");
    } catch (InvalidPathExpression e) {
        fail("Exception in deserialize file instance");
    }    
    
    //assertTrue(file.delete());
  }

}
