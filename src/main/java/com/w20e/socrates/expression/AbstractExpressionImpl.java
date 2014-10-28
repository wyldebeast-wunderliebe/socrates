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
 * File      : ExpressionImpl.java
 * Author    : Duco Dokter
 * Created   : Fri Aug 27 16:15:20 2004
 * Version   : $Id: ExpressionImpl.java,v 1.3 2006/11/14 16:09:45 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 *
 */

package com.w20e.socrates.expression;

/**
 *
 */
public abstract class AbstractExpressionImpl implements Expression {

	/**
	 * Return the string representation of this expression.
	 * 
	 * @return the string representation of the expression.
	 */
	@Override
	public abstract String toString();

	/**
	 * Get the type for this expression. In case of, for example, operations,
	 * this should return the name of the operation, like 'Multiply', 'And',
	 * etc. The default implementation will return the class name, without the
	 * namespace.
	 * 
	 * @return the type of the operation.
	 */
	@Override
	public final String getType() {

		return this.getClass().getName().substring(
				this.getClass().getName().lastIndexOf(".") + 1);
	}
}
