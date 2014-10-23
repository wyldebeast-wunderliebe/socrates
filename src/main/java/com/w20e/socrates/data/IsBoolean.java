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

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class IsBoolean implements Restriction {

	/**
	 * Evaluate whether this string could be a boolean value in value space.
	 * 
	 * @param value
	 *            Object to test. This will yield true IFF the string
	 *            representation of the object is "0", "1", "true" or "false"
	 *            (case insensitive).
	 * @return boolean value indicating whether this string could be a boolean.
	 */
	public final boolean eval(final Object value) {

		if ("0".equals(value.toString()) || "1".equals(value.toString())
				|| "true".equals(value.toString().toLowerCase())
				|| "false".equals(value.toString().toLowerCase())) {
			return true;
		}
		return false;
	}
}
