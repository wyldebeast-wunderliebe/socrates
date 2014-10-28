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

/**
 * Implementation of the Exponent (e^x) operation.
 */
public class Exp extends AbstractUnaryOperationImpl {

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

		return "exp(" + getLeftOperand().toString() + ")";
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

		return new XNumber(new Double(Math.exp(getLeftOperand().eval()
				.toNumber().doubleValue())));
	}
}
