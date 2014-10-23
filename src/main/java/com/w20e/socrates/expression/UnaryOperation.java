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
 * File      : UnaryOperation.java
 * Author    : Duco Dokter
 * Created   : Wed Sep  1 15:54:19 2004
 * Version   : $Id: UnaryOperation.java,v 1.1 2007-08-08 08:11:46 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 *
 */

package com.w20e.socrates.expression;

/**
 * Interface for unary operations, like the unary minus, or the negation
 * operation.
 */
public interface UnaryOperation extends Expression {

	/**
	 * Get the left (and only) operand.
	 * 
	 * @return The <code>Expression</code> that is the left (and only) operand.
	 */
	Expression getLeftOperand();

	/**
	 * Set the left (and only) operand.
	 * 
	 * @param op
	 *            The <code>Expression</code> to use for left operand.
	 */
	void setLeftOperand(Expression operand);

}
