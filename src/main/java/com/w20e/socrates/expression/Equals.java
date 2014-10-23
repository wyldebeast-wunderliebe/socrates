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
 * File      : Equals.java
 * Classname :
 * Author    : Duco Dokter
 * Date      :
 * Version   :
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the 'equals' operation.
 */
public class Equals extends AbstractOperationImpl {

	/**
	 * Return boolean evaluation of this expression.
	 * 
	 * @return true if left and right operand are equal, according to the left
	 *         operand's equals method.
	 */
	public final boolean toBoolean() {

		return getLeftOperand().eval().equals(getRightOperand().eval());
	}

	/**
	 * Return the string representation of the expression. This is the string
	 * representation of the left operand, ' == ' and the string representation
	 * of the right operand.
	 * 
	 * @return the string representation of this expression.
	 */
	public final String toString() {

		return getLeftOperand().toString() + " == "
				+ getRightOperand().toString();
	}

	/**
	 * Return the XObject representation of this operation.
	 * 
	 * @return the XObject that is the result of this operation. This is an
	 *         XBoolean.
	 */
	public final XObject eval() {

		return new XBoolean(toBoolean());
	}
}
