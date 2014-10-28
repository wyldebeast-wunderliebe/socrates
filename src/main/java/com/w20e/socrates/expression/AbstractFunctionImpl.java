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
public abstract class AbstractFunctionImpl extends AbstractExpressionImpl
		implements Function {

	/**
	 * Left operand for implementation. Defaults to XBoolean false.
	 */
	private Expression[] args = {};

	/**
	 * Get the arguments for this operation.
	 * 
	 * @return the operands of the operation.
	 */
	@Override
	public final Expression[] getOperands() {

		return this.args.clone();
	}

	/**
	 * Set the operands for the operation.
	 * 
	 * @param newArgs
	 *            the operands for the operation.
	 */
	public final void setOperands(final Expression[] newArgs) {

		this.args = newArgs.clone();
	}

	/**
	 * Return string representation of operands.
	 * 
	 * @return String representation of operands.
	 */
	public String getOperandsString() {

		final StringBuffer buf = new StringBuffer();

		for (Expression e : this.args) {
			buf.append(e.toString());
			buf.append(',');
		}

		return buf.toString();
	}
}
