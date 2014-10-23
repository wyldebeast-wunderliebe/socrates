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
 * File      : XBoolean.java
 * Classname : XBoolean
 * Author    : Duco Dokter
 * Date      : 14 Jan 2005
 * Version   : $Id: XBoolean.java,v 1.18 2006/11/23 10:48:28 dokter Exp $
 */

package com.w20e.socrates.expression;

/**
 * Implementation of boolean as XObject.
 */
public class XBoolean extends AbstractXObjectImpl {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Wrapped boolean.
	 */
	private final boolean bool;

	/**
	 * Constructor using boolean primitive type.
	 * 
	 * @param booleanVal
	 *            initial boolean value for this object.
	 */
	public XBoolean(final boolean booleanVal) {
		super();
		this.bool = booleanVal;
	}

	/**
	 * Constructor using boolean object type.
	 * 
	 * @param booleanObj
	 *            initial boolean value for this object.
	 */
	public XBoolean(final Boolean booleanObj) {
		super();
		if (booleanObj == null) {
			this.bool = false;
		} else {
			this.bool = booleanObj.booleanValue();
		}
	}

	/**
	 * Get the Number representation for this object. The boolean is converted
	 * to a number as follows: if the wrapped value is false, zero is returned,
	 * otherwise one is returned.
	 * 
	 * @return the wrapped <code>Number</code> object
	 */
	public final Number toNumber() {

		if (this.bool) {

			return new Double(1);
		}

		return new Double(0);
	}

	/**
	 * The signature for this method is that it should return 0 if the
	 * expressions are equal, a value less than zero if the expression provided
	 * as an argument is greater than the expression, and a value bigger than
	 * zero if the expression provided as argument is less than the expression.
	 * 
	 * @param expr
	 *            The expression to compare to.
	 * @return integer value indicating comparison result.
	 */
	public final int compareTo(final Expression expr) {

		if (expr instanceof Undef) {
			return -1;
		}

		if ((this.bool && expr.toBoolean())
				|| (!this.bool && !expr.toBoolean())) {
			return 0;
		} else if (!this.bool && expr.toBoolean()) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Compares this XObject to the specified object. If the argument is null,
	 * false will be returned. Otherwise the comparison will be boolean wise.
	 * 
	 * @param obj
	 *            The expression to use in equality check.
	 * @return the boolean representation of this object.
	 */
	public final boolean equals(final Object obj) {

		if (!(obj instanceof Expression)) {
			return false;
		}

		return this.compareTo((Expression) obj) == 0;
	}

	/**
	 * Return the boolean representation of this object.
	 * 
	 * @return the boolean representation of this object.
	 */
	public final boolean toBoolean() {

		return this.bool;
	}

	/**
	 * Return the string representation of this object.
	 * 
	 * @return 'false()' if this object evaluates to false, 'true()' otherwise.
	 */
	public final String toString() {

		return Boolean.toString(this.bool);
	}

	/**
	 * Return the underlying object. This is always a Boolean object.
	 * 
	 * @return Boolean object.
	 */
	public final Object toObject() {

		return Boolean.valueOf(this.bool);
	}

}
