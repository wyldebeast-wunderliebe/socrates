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

import java.util.Locale;
import java.util.Map;

import com.w20e.socrates.data.RestrictionViolation;
import com.w20e.socrates.data.TransformationException;
import com.w20e.socrates.data.TypeChecker;

public class Input extends ControlImpl {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct input type.
	 */
	public Input(String id) {
		super(id);
		setType("input");
	}

	/**
	 * Process data. This will return the String representation of the data
	 * for this control's id in the map. If the value in the data map is empty,
	 * or null, null will be returned.
	 */
	@Override
	public Object processInput(Map<String, Object> data) {

	    if (!data.containsKey(getId())) {
	        throw new RuntimeException("No key found for " + getId());
	    }
	    
	    if (data.get(getId()) == null || "".equals(data.get(getId()))) {
	        return null;
	    }

		return data.get(getId());
	}

    /**
     * Format the underlying value into something the user might understand.
     */
    @Override
	public Object getDisplayValue(Object value, Class<?> datatype, Locale locale) {

        try {
            return TypeChecker.evaluateLexical(datatype, value, locale);
        } catch (TransformationException e) {
            return "";
        } catch (RestrictionViolation e) {
            return "";
        }
    }

}
