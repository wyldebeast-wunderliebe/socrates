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
 * File      : Expression.java
 * Classname : Expression
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: Expression.java,v 1.1 2007-08-08 08:11:46 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 *
 */

package com.w20e.socrates.expression;

/**
 * The Expression interface defines the minimum requirements for an expression.
 * These requirements will be shared by all implementing classes like Operation
 * and XObject, so as to be able to evaluate any of these.
 */
public interface Expression {

	/**
	 * Evaluate the expression to an XResult, so as to be able to process
	 * further, for instance compare with another expression, etc. The result 
	 * should never be null. In case of undetermined results, use an 
	 * implementation of XObject that represents that.
	 * 
	 * @return The <code>XObject</code> that is the result of the evaluation.
	 */
	XObject eval();

	/**
	 * Return the string representation of this expression. This does not
	 * evaluate the expression, but returns a string representation of the
	 * full expression.
	 * 
	 * @return the string representation of the expression.
	 */
	String toString();

	/**
	 * Return the result of this expression as boolean value. Usually, for
	 * literals like strings and numeric values, this means that for null, ""
	 * and 0 'false' should be returned, otherwise 'true'.
	 * 
	 * @return The boolean value of the evaluated expression.
	 */
	boolean toBoolean();

	/**
	 * Get the type for this expression. In case of, for example, operations,
	 * this should return the name of the operation, like 'Multiply', 'And',
	 * etc.
	 * 
	 * @return String
	 */
	String getType();
}
