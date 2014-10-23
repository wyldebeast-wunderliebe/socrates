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
 * To string operation.
 */
public class ToString implements Transformation {

	/**
	 * Return string representation of the given object. This is done by calling
	 * the <code>toString</code> method of the object. In case the object is
	 * null, null returned.
	 * 
	 * @param obj
	 *            object to transform.
	 * @return transformed object.
	 */
	public final Object transform(final Object obj) {

		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public final Object transform(final Object obj, final Locale locale) {
		return this.transform(obj);
	}

}
