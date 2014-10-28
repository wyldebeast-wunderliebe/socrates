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
 * File      : UnaryOperationImpl.java
 * Classname : UnaryOperationImpl
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.9 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation class for unary oparations, like unary minus, or negation.
 */
public abstract class AbstractUnaryOperationImpl extends AbstractExpressionImpl
		implements UnaryOperation {

	/**
	 * Left operand for implementation. Defaults to XBoolean false.
	 */
	private Expression lVal;

	/**
	 * Get the left operand for this operation.
	 * 
	 * @return the left operand of the operation.
	 */
	@Override
	public final Expression getLeftOperand() {

		if (this.lVal == null) {
			return AbstractOperationImpl.NULL;
		}
		return this.lVal;
	}

	/**
	 * Set the left operand for the operation.
	 * 
	 * @param val
	 *            the left operand for the operation.
	 */
	@Override
	public final void setLeftOperand(final Expression val) {

		this.lVal = val;
	}
}
