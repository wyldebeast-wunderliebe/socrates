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
 * File      : Modulo.java
 * Classname : Modulo
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: Modulo.java,v 1.9 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the Modulo operation.
 */
public class Modulo extends AbstractOperationImpl {

	/**
	 * Return boolean representation of this operation.
	 * 
	 * @return boolean representation of this operation.
	 */
	@Override
	public final boolean toBoolean() {

		return eval().toBoolean();
	}

	/**
	 * Return String representation of this operation.
	 * 
	 * @return String representation of this operation.
	 */
	@Override
	public final String toString() {

		return getLeftOperand().toString() + " % "
				+ getRightOperand().toString();
	}

	/**
	 * Return the XObject that is the evaluated result of the Module operation.
	 * 
	 * @return the evaluated result as XObject.
	 */
	@Override
	public final XObject eval() {

		return new XNumber(new Double(getLeftOperand().eval().toNumber()
				.doubleValue()
				% getRightOperand().eval().toNumber().doubleValue()));
	}
}
