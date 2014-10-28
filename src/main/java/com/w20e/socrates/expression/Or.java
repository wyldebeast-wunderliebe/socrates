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
 * File      : Or.java
 * Author    : Duco Dokter
 * Created   : Fri Aug 27 16:16:38 2004
 * Version   : $Id: Or.java,v 1.7 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the or operation.
 */
public class Or extends AbstractOperationImpl {

	/**
	 * Return the boolean representation of this operation.
	 * 
	 * @return the boolean representation of this operation.
	 */
	@Override
	public final boolean toBoolean() {

		return getLeftOperand().toBoolean() || getRightOperand().toBoolean();
	}

	/**
	 * Return the string representation of this operation.
	 * 
	 * @return the string representation of this operation.
	 */
	@Override
	public final String toString() {

		return getLeftOperand().toString() + " or "
				+ getRightOperand().toString();
	}

	/**
	 * Return the evaluated expression.
	 * 
	 * @return the evaluated expression.
	 */
	@Override
	public final XObject eval() {

		return new XBoolean(toBoolean());
	}
}
