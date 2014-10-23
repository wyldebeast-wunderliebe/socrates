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
 * File      : OperationImpl.java
 * Classname :
 * Author    : Duco Dokter
 * Date      :
 * Version   :
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 *
 */

package com.w20e.socrates.expression;

/**
 * @author D.A.Dokter
 * @version $id: $
 * 
 *          Implementation class for binary operations.
 */
public abstract class AbstractOperationImpl extends AbstractExpressionImpl implements Operation {

	/**
	 * Left operand for implementation. Defaults to XBoolean false.
	 */
	private Expression lVal;

	/**
	 * Right operand for implementation. Defaults to XBoolean false.
	 */
	private Expression rVal;

	/**
	 * NULL holder for empty operands.
	 */
	protected static final Undef NULL = new Undef();

	/**
	 * Get the left operand for this operation.
	 * 
	 * @return the left operand of the operation.
	 */
	public final Expression getLeftOperand() {

		if (this.lVal == null) {
			return AbstractOperationImpl.NULL;
		}
		return this.lVal;
	}

	/**
	 * Get the right operand for this operation.
	 * 
	 * @return the right operand of the operation.
	 */
	public final Expression getRightOperand() {

		if (this.rVal == null) {
			return AbstractOperationImpl.NULL;
		}
		return this.rVal;
	}

	/**
	 * Set the left operand for the operation.
	 * 
	 * @param lval
	 *            the left operand for the operation.
	 */
	public final void setLeftOperand(final Expression val) {

		this.lVal = val;
	}

	/**
	 * Set the right operand for the operation.
	 * 
	 * @param rval
	 *            the right operand for the operation.
	 */
	public final void setRightOperand(final Expression val) {

		this.rVal = val;
	}
}
