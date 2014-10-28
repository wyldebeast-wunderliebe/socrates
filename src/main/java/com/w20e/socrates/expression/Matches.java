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
 * File      : Matches.java
 * Classname : Matches
 * Author    : Huub Bouma
 * Date      : 7 Mar 2006
 * Version   : $Revision: 1.4 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import java.util.regex.Pattern;

/**
 * Implementation of the (Xpath) Matches operation.
 */
public class Matches extends AbstractOperationImpl {

	/**
	 * Return the boolean result of evaluating this expression.
	 * 
	 * @return true if the operation is evaluated to a non-zero value, else
	 *         return false.
	 */
	@Override
	public final boolean toBoolean() {

		return eval().toBoolean();
	}

	/**
	 * Return the String representation of this operation.
	 * 
	 * @return the string representation of this operation.
	 */
	@Override
	public final String toString() {

		return "matches(" + getLeftOperand().toString() + ", "
				+ getRightOperand().toString() + ")";
	}

	/**
	 * Evaluate the operation, by trying to cast left and right operant to an
	 * XNumber, and subtracting the right operand from the left.
	 * 
	 * @return the XObject that is the result of this evaluation. In case of
	 *         this operation this is always an XNumber.
	 */
	@Override
	public final XObject eval() {

		final String str = getLeftOperand().eval().toString();
		final String pattern = getRightOperand().eval().toString();

		return new XBoolean(Pattern.matches(pattern, str));
	}
}
