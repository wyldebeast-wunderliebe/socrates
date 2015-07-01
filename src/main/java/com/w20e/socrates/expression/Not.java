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
 * File      : Not.java
 * Classname : Not
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Id: Not.java,v 1.7 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of negation operator.
 */
public class Not extends AbstractUnaryOperationImpl {

	/**
	 * Return boolean result of the negation operation.
	 * 
	 * @return true iff the operand evaluates to false;
	 */
	@Override
	public final boolean toBoolean() {

		return !getLeftOperand().toBoolean();
	}

	/**
	 * Return the string representation of the expression.
	 * 
	 * @return '!' and the string representation of the operand.
	 */
	@Override
	public final String toString() {

		return "not(" + getLeftOperand().toString() + ")";
	}

	/**
	 * Evaluate the operation to an XObject. This will always be an XBoolean.
	 * 
	 * @return the result of evaluating the operation.
	 */
	@Override
	public final XObject eval() {

		return new XBoolean(toBoolean());
	}
}
