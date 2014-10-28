package com.w20e.socrates.factories;

import java.net.URI;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.Questionnaire;

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
			Assert.assertEquals("/bar > 666", q.getModel("foo").getItemProperties("p1").getRelevant().toString());

		} catch (InvalidPathExpression e) {
			Assert.fail(e.getMessage());
		}
	}		
}
