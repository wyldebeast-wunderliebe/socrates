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

import java.util.Map;

import com.w20e.socrates.model.ConstraintViolation;

/**
 * Select control.
 * @TODO enable multiple values
 * @author dokter
 *
 */
public class Select extends SelectionControl {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construct select type.
	 */
	public Select() {
		super();
		setType("select");
	}

	/**
	 * Construct select type.
	 */
	public Select(String id) {
		super(id);
		setType("select");
	}

	/**
	 * Allow multiple choices?
	 */
	private boolean multiple = false;

	/**
	 * Set the appearance to full.
	 */
	public void setFullAppearance() {

		setProperty("appearance", "FULL");
	}

	/**
	 * Set the appearance to minimal.
	 */
	public void setMinimalAppearance() {

		setProperty("appearance", "MINIMAL");
	}

	/**
	 * Set the appearance to compact;
	 */
	public void setCompactAppearance() {

		setProperty("appearance", "COMPACT");
	}

	/**
	 * Does the select allow multiple choice?
	 * 
	 * @return multiple or no.
	 */
	public boolean isMultiple() {

		return this.multiple;
	}

	/**
	 * Set multiple choice.
	 * 
	 * @param multiple
	 */
	public void setMultiple(final boolean newMultiple) {

		this.multiple = newMultiple;
	}

	/**
	 * Process select input.
	 * @throws ConstraintViolation 
	 */
	// @todo implement multiple behaviour. 
	@Override
	public final Object processInput(Map<String, Object> data) {

	    if (!data.containsKey(getId())) {
	        throw new RuntimeException("No key found for " + getId());
	    }

	    if (data.get(getId()) == null || "".equals(data.get(getId()))) {
            return null;
        }

		return data.get(getId());
	}
}
