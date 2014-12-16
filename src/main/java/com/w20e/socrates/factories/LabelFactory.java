package com.w20e.socrates.factories;

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

import com.w20e.socrates.rendering.Label;


public class LabelFactory extends AbstractObjectCreationFactory<Label> {

	/**
	 * Create WoliWeb specific <code>ItemWrapper</code>s.
	 * 
	 * @author Duco Dokter
	 * @param attrs
	 *            Attributes of the items stumbled upon.
	 * @throws Exception
	 *             if stuff fails
	 * @return an ItemWrapper
	 */
	public final Label createObject(final Attributes attrs) throws Exception {
		
		Label label = new Label();
		
		if (!"".equals(attrs.getValue("i18n:msgid"))) {
			label.setMsgid(attrs.getValue("i18n:msgid"));
		}

		if (!"".equals(attrs.getValue("i18n:msgctx"))) {
			label.setMsgctx(attrs.getValue("i18n:msgctx"));
		}

		return label;
	}
}
