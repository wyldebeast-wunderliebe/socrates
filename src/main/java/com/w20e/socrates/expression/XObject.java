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
 * File      : XObject.java
 * Classname : XObject
 * Author    : Duco Dokter
 * Date      : 18 Jan 2005
 * Version   : $Id: XObject.java,v 1.1 2007-08-08 08:11:46 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;


/**
 * Implementation class for XObject.
 */
public interface XObject extends Expression {

	/**
	 * The signature for this method is that it should return 0 if the
	 * expressions are equal, a value less than zero if the expression provided
	 * as an argument is greater than the expression, and a value bigger than
	 * zero if the expression provided as argument is less than the expression.
	 * 
	 * @param expr
	 *            the Expression to compare to.
	 * @return integer result of comparison
	 */
	int compareTo(Expression expr);

	/**
	 * Return the numeric representation of the XObject.
	 * 
	 * @return the numeric representation of the XObject.
	 */
	Number toNumber();

	/**
	 * Return the underlying object.
	 * 
	 * @return Object wrapped by this XObject
	 */
	Object toObject();
}
