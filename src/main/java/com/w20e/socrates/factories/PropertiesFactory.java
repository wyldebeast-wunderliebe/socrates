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
 * Version   : $Revision: 1.3 $
 * Copyright : Wyldebeast & Wunderliebe
 */

package com.w20e.socrates.factories;

import java.util.logging.Logger;

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

import com.w20e.socrates.model.ItemPropertiesImpl;


/**
 * Factory class for creating Nodes.
 *
 * @author Duco Dokter
 * @version 1.0
 */
public class PropertiesFactory extends AbstractObjectCreationFactory<ItemPropertiesImpl> {

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger.getLogger(PropertiesFactory.class.getName());

	/**
	 * Create Node
	 * @param attrs
	 *            Attributes of the items stumbled upon.
	 * @throws Exception
	 *             if stuff fails
	 * @return an ItemWrapper
	 */
	public final ItemPropertiesImpl createObject(final Attributes attrs) throws Exception {

		LOGGER.fine("Creating properties " + attrs.getValue("id"));
		
		final ItemPropertiesImpl props = new ItemPropertiesImpl(attrs.getValue("id"));
		
		return props;
	}

}
