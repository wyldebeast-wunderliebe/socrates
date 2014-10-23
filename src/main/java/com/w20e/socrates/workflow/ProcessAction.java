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
 * File      : ProcessAction.java
 * Classname : ProcessAction
 * Author    : Duco Dokter
 * Created   : Sat Jan 29 15:56:49 2005
 * Version   : $Revision: 1.6 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

/**
 * Interface for process actions. The action will yield an action result,
 * determining success or failure. The action may provide an indication of it's
 * success by means of altering the process context.
 * 
 * @author <a href="mailto:dokter@w20e.com">Duco Dokter</a>
 * @version $Revision: 1.6 $
 */
public interface ProcessAction extends ProcessState {

	/**
	 * Execute the action. This should throw an exception when the action result
	 * cannot be determined in terms of ActionResult.
	 * 
	 * @throws ActionExecException
	 *             whenever something goes wrong...
	 * @param context
	 *            a <code>ProcessContext</code> value
	 * @return the result of this action
	 */
	ActionResult exec(ProcessContext context) throws ActionExecException;

	/**
	 * All actions within a process(or) should have a unique ID.
	 * 
	 * @return a <code>String</code> value
	 */
	String getId();

	/**
	 * Retrieve property value;
	 * @param key
	 * @return Object for this key, or null if not found.
	 */
	String getProperty(String key);

	/**
	 * Set a property for the action. This enables configuration of individual actions.
	 * @param key Name of property
	 * @param value Property value
	 */
	void setProperty(String key, String value);
}
