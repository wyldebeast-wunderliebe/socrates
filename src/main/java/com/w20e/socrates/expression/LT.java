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
 * File      : LT.java
 * Classname : LT
 * Author    : Duco Dokter
 * Date      : 15 Jan 2005
 * Version   : $Id: LT.java,v 1.4 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the 'less than' operation.
 */
public class LT extends AbstractOperationImpl {

	/**
	 * Return the boolean representation of the evaluated operation.
	 * 
	 * @return the boolean representation of the evaluated operation.
	 */
	public final boolean toBoolean() {

		return getLeftOperand().eval().compareTo(getRightOperand().eval()) < 0;
	}

/**
   * Return the strin representation of the operation. This is the
   * string representation of the left operand, '<', and the string
   * representation of the right operand.
   *
   * @return the boolean representation of the evaluated operation.
   */
	public final String toString() {

		return getLeftOperand().toString() + " < "
				+ getRightOperand().toString();
	}

	/**
	 * Evaluate the operation to an XObject. This is an instance of XBoolean.
	 * 
	 * @return the XBoolean result of evaluating the operation.
	 */
	public final XObject eval() {

		return new XBoolean(toBoolean());
	}
}
