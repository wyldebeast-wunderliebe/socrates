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
 * File      : And.java
 * Classname : And
 * Author    : Duco Dokter
 * Date      : 17 Jan 2005
 * Version   : $Id: And.java,v 1.4 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of And operation.
 * 
 * @author <a href="mailto:dokter@w20e.com">Duco Dokter</a>
 * @version 1.0
 */
public class IfThenElse extends AbstractTernaryOperationImpl {

	/**
	 * Return the boolean representation of this operation.
	 * 
	 * @return the boolean representation of this operation.
	 */
	public final boolean toBoolean() {

		if (getLeftOperand().toBoolean()) {
			return getMiddleOperand().toBoolean();
		}
		return getRightOperand().toBoolean();
	}

	/**
	 * Return the string representation of this operation.
	 * 
	 * @return the string representation of this operation.
	 */
	public final String toString() {

		return getLeftOperand().toString() + " ? "
				+ getMiddleOperand().toString() + " : "
				+ getRightOperand().toString();
	}

	/**
	 * Evaluate this operation to an XObject.
	 * 
	 * @return the XObject evaluation of this operation.
	 */
	public final XObject eval() {

		if (getLeftOperand().toBoolean()) {
			return getMiddleOperand().eval();
		}
		return getRightOperand().eval();
	}
}
