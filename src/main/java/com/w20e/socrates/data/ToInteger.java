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
 * Transformation to integer.
 */
public class ToInteger implements Transformation {

	/**
	 * Try to convert the given argument to an Integer. If the argument is null,
	 * null is returned.
	 * 
	 * @param obj
	 *            object to convert.
	 * @return Integer object.
	 * @throws TransformationException
	 */
	@Override
	public final Object transform(final Object obj)
			throws TransformationException {

		if (obj == null) {
			return null;
		}

		if (obj instanceof Number) {
			return Integer.valueOf(((Number) obj).intValue());
		}

		try {
			return Integer.valueOf(Double.valueOf(obj.toString()).intValue());
		} catch (NumberFormatException nfe) {
			throw new TransformationException(nfe.getMessage());
		}
	}

	@Override
	public final Object transform(final Object obj, final Locale locale)
			throws TransformationException {
		return this.transform(obj);
	}

}
