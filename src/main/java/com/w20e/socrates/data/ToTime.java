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

import com.w20e.socrates.expression.XObject;
import com.w20e.socrates.expression.XString;

/**
 * @see com.w20e.socrates.data.Transformation
 * @author helmantel
 */
public class ToTime implements LexicalTransformation {

	/**
	 * Execute a transformation on the time parameter.
	 * 
	 * @param obj
	 *            The object to transform
	 * @return Object The transformed object
	 */
	@Override
	public final XString transform(final XObject obj, final Locale locale) {

		if (obj == null || "".equals(obj.toString())) {
			return new XString("00:00");
		}

		String hours = "00";
		String minutes = "00";

		if (obj.toString().indexOf(':') != -1) {
			hours = obj.toString().substring(0, obj.toString().indexOf(':'));
			minutes = obj.toString().substring(obj.toString().indexOf(':') + 1);
		} else {
			hours = obj.toString();
		}

		return new XString(hours + ":" + minutes);
	}

}
