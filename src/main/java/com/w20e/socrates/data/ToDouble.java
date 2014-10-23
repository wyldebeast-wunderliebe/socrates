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

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class ToDouble implements Transformation {

	/**
	 * Return Double value for this object. If the argument is null, null is
	 * returned.
	 * 
	 * @param obj
	 *            object to convert.
	 * @return float object
	 */
	public final Object transform(final Object obj) {

		if (obj == null || "".equals(obj.toString()) || obj.toString() == null) {
			return null;
		}

		return Double.valueOf(obj.toString());
	}

	public final Object transform(final Object obj, final Locale locale) {
		try {
			return DecimalFormat.getInstance(locale)
					.parseObject(obj.toString());
		} catch (Exception e) {
			return null;
		}
	}
}
