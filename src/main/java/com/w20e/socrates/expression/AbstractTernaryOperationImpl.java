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
public abstract class AbstractTernaryOperationImpl extends
		AbstractExpressionImpl implements TernaryOperation {

	/**
	 * Left operand for implementation.
	 */
	private Expression lVal;

	/**
	 * Right operand for implementation.
	 */
	private Expression rVal;

	/**
	 * Middle operand.
	 */
	private Expression mVal;

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
			return AbstractTernaryOperationImpl.NULL;
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
			return AbstractTernaryOperationImpl.NULL;
		}
		return this.rVal;
	}

	/**
	 * Get the middle operand for this operation.
	 * 
	 * @return the middle operand of the operation.
	 */
	public final Expression getMiddleOperand() {

		if (this.mVal == null) {
			return AbstractTernaryOperationImpl.NULL;
		}
		return this.mVal;
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

	/**
	 * Set the middle operand for the operation.
	 * 
	 * @param rval
	 *            the middle operand for the operation.
	 */
	public final void setMiddleOperand(final Expression val) {

		this.mVal = val;
	}

}