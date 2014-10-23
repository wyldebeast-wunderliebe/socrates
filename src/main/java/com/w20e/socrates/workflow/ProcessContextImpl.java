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
 * File      : ProcessContextImpl.java
 * Classname : ProcessContextImpl
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 12:43:11 2005
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation class for Process context.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class ProcessContextImpl implements ProcessContext {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -5675308257805811790L;

	/**
	 * the latest result.
	 */
	private ActionResult result = new Undefined();

	/**
	 * Hold current action.
	 */
	private ProcessAction currentAction;

	private Map<String, Object> properties;

	public ProcessContextImpl() {

		this.properties = new HashMap<String, Object>();
	}

	/**
	 * Get the name property, or if not set, the default object.
	 * 
	 * @param name
	 *            a <code>String</code> value
	 * @param defaultValue
	 *            an <code>Object</code> value
	 * @return an <code>Object</code> value
	 */
	public final Object getProperty(final String name, final Object defaultValue) {

		if (this.properties.containsKey(name)) {
			return this.properties.get(name);
		}

		return defaultValue;
	}

	/**
	 * Get the named property.
	 * 
	 * @param name
	 *            a <code>String</code> value
	 * @return an <code>Object</code> value
	 */
	public final Object getProperty(final String name) {

		return this.properties.get(name);
	}

	/**
	 * Set the property.
	 * 
	 * @param name
	 *            a <code>String</code> value
	 * @param value
	 *            an <code>Object</code> value
	 * @return an <code>Object</code> value
	 */
	public final Object setProperty(final String name, final Object value) {

		return this.properties.put(name, value);
	}

	
	public final Object unsetProperty(final String name) {

		return this.properties.remove(name);
	}

	
	/**
	 * Add all properties from the context given as argument.
	 * 
	 * @param context
	 *            a <code>ProcessContext</code> value
	 */
	public final void addContext(final ProcessContext context) {

		this.properties.putAll(context.getProperties());
	}

	/**
	 * Get the last action result.
	 * 
	 * @return an <code>ActionResult</code> value
	 */
	public final ActionResult getResult() {

		return this.result;
	}

	/**
	 * Set the action result.
	 * 
	 * @param newResult
	 *            an <code>ActionResult</code> value
	 */
	public final void setResult(final ActionResult newResult) {

		this.result = newResult;
	}

	/**
	 * Return the current action.
	 * 
	 * @return current action
	 */
	public final ProcessAction getCurrentAction() {

		return this.currentAction;
	}

	/**
	 * Set the current action.
	 * 
	 * @param action
	 *            current action.
	 */
	public final void setCurrentAction(final ProcessAction action) {

		this.currentAction = action;
	}

	@Override
	public Map<String, Object> getProperties() {

		return this.properties;

	}
}
