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
 * File      : ActionFactory.java
 * Classname : ActionFactory
 * Author    : Duco Dokter
 * Created   : Thu Jan 27 16:56:23 2005
 * Version   : $Revision: 1.13 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

/**
 * Action factory for use with the digester workflow factory.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class ActionFactory extends AbstractObjectCreationFactory<Object> {

	/**
	 * Holds the actions, that can statically be retrieved by other factories,
	 * like the MappingFactory.
	 */
	private static Map<String, ProcessState> actions = new HashMap<String, ProcessState>();

	/**
	 * Create a new ProcessAction.
	 * 
	 * @param attrs
	 *            the attributes on this element.
	 * @return an new ProcessAction
	 * @exception Exception
	 *                if the action has no class attribute or no id attribute.
	 */
	public final Object createObject(final Attributes attrs) throws Exception {

		if (actions.containsKey(attrs.getValue("idref"))) {
			return ActionFactory.getAction(attrs.getValue("idref"));
		}

		if (attrs.getValue("class") == null || attrs.getValue("id") == null
				|| "".equals(attrs.getValue("class"))
				|| "".equals(attrs.getValue("id"))) {
			throw new Exception("No class or id specified!");
		}

		final Class<?>[] args = { Class.forName(String.class.getName()) };

		final Constructor<?> constr = Class.forName(attrs.getValue("class"))
				.getConstructor(args);

		final Object[] objArgs = { attrs.getValue("id") };

		final ProcessAction action = (ProcessAction) constr
				.newInstance(objArgs);

		actions.put(action.getId(), action);

		return action;
	}

	/**
	 * Static access to previously created actions.
	 * 
	 * @param id
	 *            the id of the action to retrieve
	 * @return a <code>ProcessAction</code> value
	 */
	public static ProcessState getAction(final String id) {

		return actions.get(id);
	}

	/**
	 * Static access to action list.
	 * 
	 * @param id
	 *            the id of the action to retrieve
	 * @param action
	 *            <code>ProcessState</code> value
	 */
	public static void putAction(final String id, final ProcessState action) {

		actions.put(id, action);
	}
}
