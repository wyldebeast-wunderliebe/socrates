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
 * File      : GT.java
 * Classname : GT
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: GT.java,v 1.5 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the 'greater than' operation.
 */
public class GT extends AbstractOperationImpl {

	/**
	 * Return boolean evaluation of this expression.
	 * 
	 * @return true if left operand is greater than the right operand, according
	 *         to the left operand's <code>compareTo</code> method.
	 */
	public final boolean toBoolean() {

		return getLeftOperand().eval().compareTo(getRightOperand().eval()) > 0;
	}

	/**
	 * Return the string representation of the expression. This is the string
	 * representation of the left operand, ' > ' and the string representation
	 * of the right operand.
	 * 
	 * @return the string representation of this expression.
	 */
	public final String toString() {

		return getLeftOperand().toString() + " > "
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
