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

package com.w20e.socrates.data;

import java.util.Locale;

/**
 * @author dokter Define Transformation interface for use with DataTypes.
 */
public interface Transformation {

	/**
	 * Transform one object into another, usually from string representation
	 * into raw object value.
	 * 
	 * @param obj
	 *            object to transform
	 * @return the new object.
	 */
	Object transform(final Object obj) throws TransformationException;

	/**
	 * Transform one object into another, usually from string representation
	 * into raw object value, using locale l.
	 * 
	 * @param obj
	 *            object to transform
	 * @param l
	 *            Locale to use for transformation.
	 * @return the new object.
	 */
	Object transform(final Object obj, final Locale locale) throws TransformationException;
}
