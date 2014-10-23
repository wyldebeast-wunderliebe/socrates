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
 * File      : ProcessContext.java
 * Classname : ProcessContext
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 10:42:28 2005
 * Version   : $Revision: 1.10 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.util.Map;


/**
 * The ProcessContext defines contextual info for the Action. This is the only
 * way to provide the Action classes with contextual info, given the interface
 * for the ProcessAction. When an action has to use output, for instance to
 * render something to a screen, and receive the input from that screen, the
 * output needs to be available in the context.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public interface ProcessContext {

	/**
	 * Get the object named by 'name', or if not set, return the default value.
	 * 
	 * @param name
	 *            a <code>String</code> value
	 * @param defaultValue
	 *            an <code>Object</code> value
	 * @return an <code>Object</code> value
	 */
	Object getProperty(String name, Object defaultValue);

	/**
	 * Get the named object.
	 * 
	 * @param name
	 *            a <code>String</code> value
	 * @return an <code>Object</code> value
	 */
	Object getProperty(String name);

	/**
	 * Get all Properties. 
	 */
	Map<String, Object> getProperties();

	/**
	 * Set the property.
	 * 
	 * @param name
	 *            a <code>String</code> value
	 * @param value
	 *            an <code>Object</code> value
	 * @return an <code>Object</code> value
	 */
	Object setProperty(String name, Object value);

	
	Object unsetProperty(String name);

	
	/**
	 * Add a context to this context. This will copy all properties into this
	 * context.
	 * 
	 * @param context
	 *            a <code>ProcessContext</code> value
	 */
	void addContext(ProcessContext context);

	/**
	 * Get the current result.
	 * 
	 * @return an <code>ActionResult</code> value
	 */
	ActionResult getResult();

	/**
	 * Set the current result.
	 * 
	 * @param result
	 *            an <code>ActionResult</code> value
	 */
	void setResult(ActionResult result);

	/**
	 * Retrieve the current action from the context.
	 * 
	 * @return the current action.
	 */
	ProcessAction getCurrentAction();

	/**
	 * Set the current action.
	 * 
	 * @param action
	 *            current action
	 */
	void setCurrentAction(ProcessAction action);
}
