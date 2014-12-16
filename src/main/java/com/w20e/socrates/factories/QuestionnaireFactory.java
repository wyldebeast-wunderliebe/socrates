/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.factories;

import java.net.URI;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.model.Questionnaire;

/**
 * @Version $Revision: 1.3 $
 * 
 *          The QuestionnaireFactory defines the interface for factories
 *          creating models (Instance/Bind combinations) and rendering info for
 *          a given questionnaire.
 * 
 * @author dokter
 */
public interface QuestionnaireFactory {

	/**
	 * Create the Questionnaire identified by a URI. The URI specifies the
	 * protocol to retrieve the form definition, and all possible parts to
	 * uniquely identify the definition for this protocol. In case of the file
	 * protocol for example, the URI should look like:
	 * <code>file:[path to file]</code>.
	 * 
	 * @param url
	 *            Check comment above
	 * @return an initialized Questionnaire, consisting of a Model, Instance and
	 *         rendering configuration.
	 * @throws UnsupportedProtocolException
	 *             when the protocol given through the URI is not supported.
	 * @throws NotFoundException
	 *             when the questionnaire is not found at the given location
	 * @throws InvalidException
	 *             When the questionnaire is invalid
	 */
	Questionnaire createQuestionnaire(URI uri, Configuration cfg)
			throws UnsupportedProtocolException, NotFoundException,
			InvalidException;
}
