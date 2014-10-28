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

public class Output extends ControlImpl {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct output type.
	 */
	public Output(String id) {
		super(id);
		setType("output");
	}

	/**
	 * Nothing to do...
	 */
	@Override
	public Object processInput(Map<String, Object> data) {

		return null;
	}

	/**
	 * Return lexical value for output.
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
