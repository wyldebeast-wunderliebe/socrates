/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : WoliWebModelFactory.java
 * Classname : WoliWebModelFactory
 * Author    : Duco Dokter
 * Date      : 12 Jan 2005
 * Version   : $Revision: 1.1.1.1 $
 * Copyright : Wyldebeast & Wunderliebe
 */

package com.w20e.socrates.factories;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.Rule;
import org.xml.sax.SAXException;

import com.w20e.socrates.model.Questionnaire;
import com.w20e.socrates.rendering.Option;
import com.w20e.socrates.rendering.RenderConfigImpl;
import com.w20e.socrates.rendering.RenderConfig;


/**
 * @author dokter
 * 
 * Factory class for creating the Wageindicator version of the Model.
 */
public class XMLQuestionnaireFactory implements QuestionnaireFactory {

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(XMLQuestionnaireFactory.class.getName());

	/**
	 * Create a new Digester for the instance. This should ALWAYS happen for
	 * each parse... Seriously!
	 * 
	 * @return the digester
	 */
	private Digester createInstanceDigester() {

		Digester dig = new Digester();
		
		NodeFactory nodeFactory = new NodeFactory();
		PropertiesFactory propsFactory = new PropertiesFactory();
		ExpressionFactory exprFactory = new ExpressionFactory();
		
		dig.addObjectCreate("survey", QuestionnaireImpl.class);
		
		//dig.addRule("*/var", nodeCreateRule);
		//dig.addCallParam( "*/var", 0, "name");
		//dig.addCallParam( "*/var", 1);
		dig.addFactoryCreate("*/var", nodeFactory);
		dig.addCallMethod("*/var", "setDefaultValue", 1);
		dig.addCallParam("*/var", 0);
		dig.addSetNext("*/var", "addNode", "com.w20e.socrates.model.NodeImpl");

		//dig.addObjectCreate("*/vargroup", Node.class);
		//dig.addSetNext("*/var", "addNode");

		dig.addFactoryCreate("*/model/properties", propsFactory);
		dig.addSetNext("*/model/properties", "addProperties", "com.w20e.socrates.model.ItemPropertiesImpl");
		
		dig.addCallMethod("*/model/properties/bind", "addBind", 1);
		dig.addCallParam("*/model/properties/bind", 0);
				
		Rule exprRule = new ExpressionCreateRule(exprFactory);
		
		dig.addRule("*/properties/required", exprRule);
		dig.addCallMethod("*/properties/required", "setExpr", 1);
		dig.addCallParam("*/properties/required", 0);

		dig.addRule("*/properties/relevant", exprRule);
		dig.addCallMethod("*/properties/relevant", "setExpr", 1);
		dig.addCallParam("*/properties/relevant", 0);

		dig.addRule("*/properties/readonly", exprRule);
		dig.addCallMethod("*/properties/readonly", "setExpr", 1);
		dig.addCallParam("*/properties/readonly", 0);

		dig.addRule("*/properties/calculate", exprRule);
		dig.addCallMethod("*/properties/calculate", "setExpr", 1);
		dig.addCallParam("*/properties/calculate", 0);

		dig.addRule("*/properties/constraint", exprRule);
		dig.addCallMethod("*/properties/constraint", "setExpr", 1);
		dig.addCallParam("*/properties/constraint", 0);

		dig.addRule("*/properties/datatype", exprRule);
		dig.addCallMethod("*/properties/datatype", "setExpr", 1);
		dig.addCallParam("*/properties/datatype", 0);

		return dig;
	}

	/**
	 * Create a new Digester for the instance. This should ALWAYS happen for
	 * each parse... Really!
	 * 
	 * @return the digester
	 */
	private Digester createRenderingDigester() {

		GroupFactory groupFactory = new GroupFactory();

		Digester dig = new Digester();

		dig.addObjectCreate("survey/layout", RenderConfigImpl.class);

		dig.addFactoryCreate("*/group", groupFactory);
		dig.addSetNext("*/group", "addItem", "com.w20e.socrates.rendering.Group");
		
		dig.addObjectCreate("*/text", "com.w20e.socrates.rendering.TextBlock");
		dig.addSetProperties("*/text");
		dig.addSetNext("*/text", "addItem", "com.w20e.socrates.rendering.TextBlock");

		dig.addObjectCreate("*/select", "com.w20e.socrates.rendering.Select");
		dig.addSetProperties("*/select");
		dig.addSetNext("*/select", "addItem", "com.w20e.socrates.rendering.Select");

		dig.addObjectCreate("*/option", "com.w20e.socrates.rendering.Option");
		dig.addSetProperties("*/option");
		dig.addSetNext("*/option", "addOption", "com.w20e.socrates.rendering.Option");		
		
		dig.addCallMethod("*/label", "setLabel", 1);
		dig.addCallParam("*/label", 0);

		dig.addCallMethod("*/hint", "setHint", 1);
		dig.addCallParam("*/hint", 0);

		dig.addCallMethod("*/help", "setHelp", 1);
		dig.addCallParam("*/help", 0);

		return dig;
	}

	/**
	 * Create the Survey Model identified by a URL. The URL specifies the
	 * protocol to retrieve the questionnaire, and all possible parts to
	 * uniquely identify the questionnaire for this protocol. In case of the
	 * file protocol for example, the URL should look like: file:// [path to
	 * file].
	 * 
	 * Note that each time a new Digester is created. This is because the API
	 * sais you should...
	 * 
	 * @param url
	 *            Indicating the location of the model.
	 * @throws UnsupportedProtocolException
	 *             When the protocol in URL is not supported
	 * @return a WoliWeb model.
	 * @throws NotFoundException 
	 * @throws InvalidException 
	 * @throws
	 * @todo which protocols are supported by digester?
	 * @todo Maybe rendering/instance parsing can be made more efficient?
	 */
	@Override
	public final synchronized Questionnaire createQuestionnaire(final URI uri)
			throws UnsupportedProtocolException, NotFoundException, InvalidException {

		String uristr = uri.toString();
		
		if (uristr.startsWith("file:")) {
			
			LOGGER.fine("We have a file URI...");
			
			uristr = uristr.replace("$HOME", System.getProperty("user.home"));

			if (uristr.startsWith("file:.")) {

				if (System.getProperty("socrates.cfg.root") != null) {
					uristr = uristr.replaceAll("^file:\\.", "file:" + System.getProperty("socrates.cfg.root"));
				}
			}
		}

		LOGGER.fine("Creating model for " + uristr);
		
		try {
			QuestionnaireImpl q = (QuestionnaireImpl) createInstanceDigester()
					.parse(uristr);
			q.setRenderConfig((RenderConfig) createRenderingDigester().parse(
					uristr));
			return q;
		} catch (IOException e) {
			LOGGER.severe("Couldn't create model: " + e.getMessage());
			throw new NotFoundException(e);
		} catch (SAXException se) {
			LOGGER.severe("Couldn't create model: " + se.getMessage());
			throw new InvalidException(se);
		}
	}

}
