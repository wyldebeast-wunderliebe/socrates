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
 * File      : GTE.java
 * Author    : Duco Dokter
 * Created   : Fri Aug 27 16:16:38 2004
 * Version   : $Id: GTE.java,v 1.5 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 *
 */

package com.w20e.socrates.expression;

/**
 * Greater than or equals operation.
 */
public class GTE extends AbstractOperationImpl {

	/**
	 * This method will return a boolean value, indicating the result of the
	 * operation. The GTE checks whether the left operand is indeed greater tha
	 * or equal to the right operand. The method calls the left operand's
	 * <code>compareTo</code> to find out.
	 * 
	 * @return a <code>boolean</code> value, indicating whether the left operand
	 *         has been found to be greater tha or equal to the right operand.
	 */
	public final boolean toBoolean() {

		return getLeftOperand().eval().compareTo(getRightOperand().eval()) >= 0;
	}

	/**
	 * Return string representation of the GTE operation. This will return the
	 * string representation of the left operand, '>=' and the string
	 * representation of the right operand.
	 * 
	 * @return the string representation of this operation.
	 */
	public final String toString() {

		return getLeftOperand().toString() + " >= "
				+ getRightOperand().toString();
	}

	/**
	 * Evaluate the operation and return the XObject representation of the
	 * result.
	 * 
	 * @return the <code>XObject</code> value that is the result of the
	 *         evalation. In this case this will be an <code>XObject</code> of
	 *         type <code>XBoolean</code>.
	 */
	public final XObject eval() {

		return new XBoolean(toBoolean());
	}
}
