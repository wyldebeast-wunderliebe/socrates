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
 * File      : LTE.java
 * Classname : LTE
 * Author    : Duco Dokter
 * Date      : 17 Jan 2005
 * Version   : $Id: LTE.java,v 1.5 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implements the 'less than or equals' operation.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version $Id: LTE.java,v 1.5 2006/11/09 10:30:08 dokter Exp $
 */
public class LTE extends AbstractOperationImpl {

	/**
	 * Return the boolean representation of this operation. This will be the
	 * evaluated operation.
	 * 
	 * @return the boolean representation of the evaluated operation.
	 */
	@Override
	public final boolean toBoolean() {

		return getLeftOperand().eval().compareTo(getRightOperand().eval()) <= 0;
	}

	/**
	 * Return string representation of the LTE operation. This will return the
	 * string representation of the left operand, '<=' and the string
	 * representation of the right operand.
	 * 
	 * @return the string representation of this operation.
	 */
	@Override
	public final String toString() {

		return getLeftOperand().toString() + " <= "
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
	@Override
	public final XObject eval() {

		return new XBoolean(toBoolean());
	}
}
