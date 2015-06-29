package com.w20e.socrates.factories;

import java.io.File;
import java.net.URI;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.config.ConfigurationResource;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.NodeValidator;
import com.w20e.socrates.model.Questionnaire;
import com.w20e.socrates.rendering.Control;
import com.w20e.socrates.rendering.Group;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.Select;
import com.w20e.socrates.rendering.TextBlock;

public class TestXMLQuestionnaireFactory extends TestCase {

	private XMLQuestionnaireFactory factory;

	public TestXMLQuestionnaireFactory(String name) {
		super(name);
	}

	@Override
	public void setUp() {

		this.factory = new XMLQuestionnaireFactory();
	}

	public void testCreateModel() throws Exception {

		File file = new File("./target/test-classes/socrates-test-config.xml");
		Configuration cfg = ConfigurationResource.getInstance()
				.getConfiguration(file.toURI().toURL());

		Questionnaire q = null;

		try {
			q = this.factory.createQuestionnaire(new URI(
					"file:./target/test-classes/survey-model.xml"), cfg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Assert.assertNotNull(q.getInstance("foo").getNode("foo"));

			Assert.assertEquals("1", q.getInstance("foo").getNode("foo")
					.getValue());

			Assert.assertNotNull(q.getModel("foo").getItemPropertiesById("p0"));

			Assert.assertNotNull(q.getModel("foo").getItemPropertiesById("p1"));

			Assert.assertEquals("1",
					q.getModel("foo").getItemPropertiesById("p0").getRequired()
							.toString());

			Assert.assertEquals("/bar < 1", q.getModel("foo")
					.getItemPropertiesById("p0").getConstraint().toString());

			Assert.assertEquals("sum(1,1,)", q.getModel("foo")
					.getItemPropertiesById("p0").getCalculate().toString());

			Assert.assertEquals("333",
					q.getModel("foo").getItemPropertiesById("p0").getReadOnly()
							.toString());

			Assert.assertEquals("XSInteger", q.getModel("foo")
					.getItemPropertiesById("p0").getDatatype().getSimpleName());

			Assert.assertEquals("/bar > 666", q.getModel("foo")
					.getItemPropertiesById("p1").getRelevant().toString());

			RenderConfig rendering = q.getRenderConfig();

			Assert.assertEquals("page", rendering.getItem("grp0").getType());

			Group grp0 = (Group) rendering.getItem("grp0");

			Assert.assertEquals("input", grp0.getItem("A1").getType());

			Assert.assertEquals("bar", grp0.getItem("A1").getProperty("foo"));

			Assert.assertEquals("select", grp0.getItem("pan").getType());

			Select pan = (Select) grp0.getItem("pan");

			Assert.assertEquals(3, pan.getOptions().size());

			Select swr = (Select) grp0.getItem("select_with_ref");

			Assert.assertEquals(2, swr.getOptions().size());

			Assert.assertEquals("Some text...",
					((TextBlock) grp0.getItem("txt")).getText().toString());

			Assert.assertTrue(NodeValidator.isRequired(q.getModel("foo")
					.getItemPropertiesById("pipo"), q
					.getInstance("foo"), q.getModel("foo")));

			Control ctrl = (Control) grp0.getItem("A1");

			Assert.assertTrue(NodeValidator.isRequired(q.getModel("foo")
					.getItemProperties(ctrl.getBind()), q.getInstance("foo"), q
					.getModel("foo")));

			Group grp2 = (Group) rendering.getItem("grp2");

			Group grp3 = (Group) grp2.getItem("grp3");

			Assert.assertEquals(2, ((Select) grp3.getItem("select_grp3_1"))
					.getOptions().size());
			Assert.assertEquals(2, ((Select) grp3.getItem("select_grp3_2"))
					.getOptions().size());
			Assert.assertEquals(2, ((Select) grp3.getItem("select_grp3_2"))
					.getOptions().size());

			Assert.assertFalse(NodeValidator.isRelevant(q.getModel("foo")
					.getItemPropertiesById("p1"), q.getInstance("foo"), q
					.getModel("foo")));

			q.getInstance("foo").getNode("bar").setValue(667);

			Assert.assertTrue(NodeValidator.isRelevant(q.getModel("foo")
					.getItemPropertiesById("p1"), q.getInstance("foo"), q
					.getModel("foo")));

			Assert.assertEquals(1, NodeValidator.getValue(q.getInstance("foo")
					.getNode("pipo"),
					q.getModel("foo").getItemPropertiesById("pipo"), q
							.getModel("foo"), q.getInstance("foo")).toNumber().intValue());

		} catch (InvalidPathExpression e) {
			Assert.fail(e.getMessage());
		}
	}
}
