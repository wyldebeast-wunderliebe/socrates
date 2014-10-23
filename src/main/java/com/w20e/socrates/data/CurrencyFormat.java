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

public class CurrencyFormat implements LexicalTransformation {

	public XObject transform(final XObject obj, final Locale locale) {
		return new XString(NumberFormat.getCurrencyInstance(locale).format(
				obj.toNumber()));
	}

}
