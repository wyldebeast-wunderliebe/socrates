package com.w20e.socrates.factories;

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;


/**
 * Factory class for creating Nodes.
 *
 * @author Duco Dokter
 * @version 1.0
 */
public class SurveyFactory extends AbstractObjectCreationFactory<QuestionnaireImpl> {

	/**
	 * Create Node
	 * @param attrs
	 *            Attributes of the items stumbled upon.
	 * @throws Exception
	 *             if stuff fails
	 * @return an ItemWrapper
	 */
	public final QuestionnaireImpl createObject(final Attributes attrs) throws Exception {

		QuestionnaireImpl q = new QuestionnaireImpl();
		
		for (int i = 0; i < attrs.getLength(); i++) {
			q.setMetaData(attrs.getQName(i), attrs.getValue(i));
		}
		
		return q;
	}

}
