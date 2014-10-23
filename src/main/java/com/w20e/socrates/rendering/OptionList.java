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

package com.w20e.socrates.rendering;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Option list implementation that holds ordered list of options.
 * 
 * @author dokter
 */
public class OptionList {

	/**
	 * Version id.
	 */
	private static final long serialVersionUID = 3706025752473989939L;

	/**
	 * Holds value for which this list is relevant. If any...
	 */
	private String refValue = SelectionControl.DEFAULT_OPTION_LIST;

	private LinkedHashMap<String,Option> options;

	public OptionList() {

		this.options = new LinkedHashMap<String, Option>();
	}

	/**
	 * Return the value for which this option becomes relevant.
	 * 
	 * @return the value
	 */
	public final String getRefValue() {
		return this.refValue;
	}

	/**
	 * Set the value for which this list becomes relevant.
	 * 
	 * @param value
	 *            the value
	 */
	public final void setRefvalue(final String value) {
		
		this.refValue = value;
	}

	/**
	 * Add an option.
	 * 
	 * @param opt
	 *            the option to add.
	 */
	public final void add(final String optId, final Option opt) {
		this.options.put(optId, opt);
	}
	
	/**
	 * Add option, using option value as id, for retrieval.
	 * @param opt
	 */
	public void addOption(final Option opt) {
		
		this.options.put(opt.getValue(), opt);
	}
	
	/**
	 * Get the option by it's value. If not found, null is returned.
	 * 
	 * @param opt
	 *            the option to add.
	 */
	public final Option get(final String optId) {
		return this.options.get(optId);
	}

	public final boolean contains(final String optId) {
		
		return this.options.containsKey(optId);
	}
	
	public final Collection<Option> getOptions() {
		return this.options.values();
	}
	
	
	public final int size() {
		return this.options.size();
	}
}
