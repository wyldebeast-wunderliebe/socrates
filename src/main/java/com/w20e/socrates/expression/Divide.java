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
 * File      : Divide.java
 * Classname : Divide
 * Author    : Duco Dokter
 * Date      : 12 Jan 2005
 * Version   : $Revision: 1.10 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the divide operation.
 */
public class Divide extends AbstractOperationImpl {

	/**
	 * Return the boolean representation of the division.
	 * 
	 * @return the boolean representation of the division.
	 */
	public final boolean toBoolean() {

		return eval().toBoolean();
	}

	/**
	 * String representation of this operation.
	 * 
	 * @return the left operand's string representation followed by ' * ' and
	 *         the right operand's string representation.
	 */
	public final String toString() {

		return getLeftOperand().toString() + " / "
				+ getRightOperand().toString();
	}

	/**
	 * Divide left operand cast to XNumber by right operand cast to XNumber.
	 * 
	 * @return the XObject that is the result of the evaluation.
	 */
	public final XObject eval() {

		return new XNumber(new Double(getLeftOperand().eval().toNumber()
				.doubleValue()
				/ getRightOperand().eval().toNumber().doubleValue()));
	}
}
