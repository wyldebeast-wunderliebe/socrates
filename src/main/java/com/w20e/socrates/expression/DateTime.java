/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : Exp.java
 * Classname : Exp
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.3 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Implementation of date function. The date function takes two arguments: a
 * date and a format string. If no date is given, the current date is used.
 * Please note that the datetime function will return a numeric value on eval,
 * that denotes the timestamp. To have a formatted value, use the str() function
 * on datetime, or use a format.
 */
public class DateTime extends AbstractFunctionImpl {

	/**
	 * Return the boolean result of evaluating this expression.
	 * 
	 * @return true if the operation is evaluated to a non-zero value, else
	 *         return false.
	 */
	public final boolean toBoolean() {

		return eval().toBoolean();
	}

	/**
	 * Return the String representation of this operation.
	 * 
	 * @return the string representation of this operation.
	 */
	public final String toString() {

		return "datetime(" + getOperandsString() + ")";
	}

	/**
	 * Evaluate the operation, by trying to format the datetime with the given
	 * format string.
	 * 
	 * @return the XObject that is the result of this evaluation. In case of
	 *         this operation this is always an XNumber.
	 */
	public final XObject eval() {

		final Expression[] ops = getOperands();
		Date date = new Date();
		SimpleDateFormat format;

		try {
			if (ops.length > 1) {
				format = new SimpleDateFormat(ops[1].eval().toString(), Locale.getDefault());
				date = format.parse(ops[0].eval().toString());
			}
		} catch (Exception e) {
			return Undef.UNDEF;
		}

		return new XNumber(Long.valueOf(date.getTime()));
	}
}
