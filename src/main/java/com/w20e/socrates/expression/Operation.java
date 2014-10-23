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
 * File      : Operation.java
 * Author    : Duco Dokter
 * Created   : Fri Aug 27 16:15:20 2004
 * Version   : $Id: Operation.java,v 1.1 2007-08-08 08:11:46 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 *
 */
public interface Operation extends Expression {

	/**
	 * Get the left operand for this expression. This itself is an expression,
	 * that may ba arbitrarily complex.
	 * 
	 * @return The left operand for this operation
	 */
	Expression getLeftOperand();

	/**
	 * Get the right operand for this expression. This itself is an expression,
	 * that may ba arbitrarily complex.
	 * 
	 * @return The right operand for this operation
	 */
	Expression getRightOperand();

	/**
	 * Set the left operand for this expression.
	 * 
	 * @param lv
	 *            The Expression to use as left operand.
	 */
	void setLeftOperand(Expression lvalue);

	/**
	 * Set the right operand for this expression.
	 * 
	 * @param rv
	 *            The Expression to use as right operand.
	 */
	void setRightOperand(Expression rvalue);

}
