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

import java.text.NumberFormat;
import java.util.Locale;

import com.w20e.socrates.expression.XObject;
import com.w20e.socrates.expression.XString;

public class DecimalFormat implements LexicalTransformation {

	@Override
	public XObject transform(final XObject obj, final Locale locale) {

		if (locale == null) {
			return new XString(NumberFormat.getInstance()
					.format(obj.toNumber()));
		}

		return new XString(NumberFormat.getInstance(locale).format(
				obj.toNumber()));
	}
}
