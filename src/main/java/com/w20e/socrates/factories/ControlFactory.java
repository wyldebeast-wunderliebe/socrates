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
 * File      : ItemFactory.java
 * Classname : ItemFactory
 * Author    : Duco Dokter
 * Date      : 17 jan 2005
 * Version   : $Revision: 1.2 $
 * Copyright : Wyldebeast & Wunderliebe
 */

package com.w20e.socrates.factories;

import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

import com.w20e.socrates.rendering.Control;

/**
 * Factory class for creating ItemWrapper classes.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter </a>
 * @version 1.0
 */
public class ControlFactory extends AbstractObjectCreationFactory<Control> {

	Configuration cfg;

	public ControlFactory(Configuration cfg) {
		this.cfg = cfg;
	}

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger.getLogger(ControlFactory.class
			.getName());

	/**
	 * Create specific control, based on configured classes. Control must have
	 * the widget attribute!
	 * 
	 * @author Duco Dokter
	 * @param attrs
	 *            Attributes of the items stumbled upon.
	 * @throws Exception
	 *             if stuff fails
	 * @return an ItemWrapper
	 */
	public final Control createObject(final Attributes attrs) throws Exception {

		LOGGER.fine("Creating control for " + attrs.getValue("type"));

		return (Control) Class
				.forName(
						cfg.getString("layout.controlclasses."
								+ attrs.getValue("type"))).newInstance();
	}
}
