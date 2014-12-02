package com.w20e.socrates.factories;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.net.URI;
import java.beans.PropertyDescriptor;
import junit.framework.Assert;
import junit.framework.TestCase;

import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.Questionnaire;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.Group;
import com.w20e.socrates.rendering.Select;


public class TestXMLQuestionnaireFactory extends TestCase {

	private XMLQuestionnaireFactory factory;

	public TestXMLQuestionnaireFactory(String name) {
		super(name);
	}

	@Override
	public void setUp() {

		this.factory = new XMLQuestionnaireFactory();
	}

	public void testCreateModel() {
		
		Questionnaire q = null;
		
		try {
			q = this.factory.createQuestionnaire(new URI("file:./target/test-classes/survey-model.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Assert.assertNotNull(q.getInstance("foo").getNode("foo"));

			Assert.assertEquals("1", q.getInstance("foo").getNode("foo").getValue());
						
			Assert.assertNotNull(q.getModel("foo").getItemProperties("p0"));

			Assert.assertNotNull(q.getModel("foo").getItemProperties("p1"));
			
			Assert.assertEquals("1", q.getModel("foo").getItemProperties("p0").getRequired().toString());
			
			Assert.assertEquals("/bar < 1", q.getModel("foo").getItemProperties("p0").getConstraint().toString());

			Assert.assertEquals("sum(1,1,)", q.getModel("foo").getItemProperties("p0").getCalculate().toString());

			Assert.assertEquals("333", q.getModel("foo").getItemProperties("p0").getReadOnly().toString());

			Assert.assertEquals("XSInteger", q.getModel("foo").getItemProperties("p0").getDatatype().getSimpleName());

			Assert.assertEquals("/bar > 666", q.getModel("foo").getItemProperties("p1").getRelevant().toString());

			RenderConfig rendering = q.getRenderConfig();
						
			Assert.assertEquals("flowgroup", rendering.getItem("grp0").getType());

			Group grp0 = (Group) rendering.getItem("grp0");
			
			Assert.assertEquals("select", grp0.getItem("pan").getType());

			Select pan = (Select) grp0.getItem("pan");
			
			Assert.assertEquals(3, pan.getOptions().size());
			
		} catch (InvalidPathExpression e) {
			Assert.fail(e.getMessage());
		}
	}		
}
