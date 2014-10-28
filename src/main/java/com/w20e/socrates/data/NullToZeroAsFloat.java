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

import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XObject;

/**
 * Convert null to 0.
 * 
 * @author dokter
 */
public class NullToZeroAsFloat implements LexicalTransformation {

	/**
	 * Convert null to zero, or leave argument as is.
	 * 
	 * @param obj
	 *            object to transform.
	 * @return 0 if the argument is null, or the argument itself otherwise.
	 */
	@Override
	public final XObject transform(final XObject obj, Locale locale) {

		if (obj.toObject() == null) {
			return new XNumber(Float.valueOf(0));
		}

		return obj;
	}
}
