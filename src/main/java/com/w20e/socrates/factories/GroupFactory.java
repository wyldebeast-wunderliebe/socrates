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

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

import com.w20e.socrates.rendering.FlowGroup;
import com.w20e.socrates.rendering.GridGroup;
import com.w20e.socrates.rendering.GroupImpl;
import com.w20e.socrates.rendering.Group;
import com.w20e.socrates.rendering.Matrix;
import com.w20e.socrates.rendering.Page;

/**
 * Factory class for creating ItemWrapper classes.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter </a>
 * @version 1.0
 */
public class GroupFactory extends AbstractObjectCreationFactory<Group> {

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger.getLogger(GroupFactory.class.getName());

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
	public final Group createObject(final Attributes attrs) throws Exception {

		// Create group
		GroupImpl group;

		LOGGER.finer("Creating new group with id " + attrs.getValue("id"));

		if ("matrix".equals(attrs.getValue("layout"))) {
			group = new Matrix(attrs.getValue("id"));

		} else if ("grid".equals(attrs.getValue("layout"))) {
			group = new GridGroup(attrs.getValue("id"));

			LOGGER.finest("It's a grid!");

			try {
				LOGGER.finest("Setting cols to " + attrs.getValue("cols"));
				((GridGroup) group).setCols(Integer.parseInt(attrs
						.getValue("cols")));
			} catch (Exception e) {
				LOGGER.severe("Didna happen");
				// no worries
			}
		} else if ("page".equals(attrs.getValue("layout"))) {
			group = new Page(attrs.getValue("id"));
		} else {
			// If it's nothing else, it's a flow.
			group = new FlowGroup(attrs.getValue("id"));

			if (attrs.getValue("orientation") != null) {
				try {
					((FlowGroup) group).setOrientation(attrs
							.getValue("orientation"));

				} catch (Exception e) {
					LOGGER.fine("Couldn't set orientation for flowgroup");
					// no worries
				}
			}
		}

		return group;
	}
}
