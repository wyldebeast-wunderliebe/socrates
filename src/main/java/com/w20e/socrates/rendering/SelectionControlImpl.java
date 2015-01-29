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
public class SelectionControlImpl extends SelectionControl {

	/**
	 * Serialization id.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Construct select type.
	 */
	public SelectionControlImpl(String id) {
		super(id);
		setType("select");
	}

	/**
	 * Process select input.
	 * @throws ConstraintViolation 
	 */
	// @todo implement multiple behaviour. 
	@Override
	public final Object processInput(Map<String, Object> data) {

		return null;
	}

}
