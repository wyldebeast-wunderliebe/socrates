package com.w20e.socrates.factories;

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

import com.w20e.socrates.rendering.Translatable;
import com.w20e.socrates.rendering.TranslatableImpl;


public class TranslatableFactory extends AbstractObjectCreationFactory<Translatable> {

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
	public final Translatable createObject(final Attributes attrs) throws Exception {
		
		TranslatableImpl label = new TranslatableImpl();
		
		if (!"".equals(attrs.getValue("i18n:msgid"))) {
			label.setMsgid(attrs.getValue("i18n:msgid"));
		}

		if (!"".equals(attrs.getValue("i18n:msgctx"))) {
			label.setMsgctx(attrs.getValue("i18n:msgctx"));
		}

		return label;
	}
}
