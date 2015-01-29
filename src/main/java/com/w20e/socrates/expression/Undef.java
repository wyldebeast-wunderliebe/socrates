/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.expression;

/**
 * Implement null operand. The Undef operand is implemented the Python way.
 */
public class Undef extends AbstractXObjectImpl {

	/**
	 * uid.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Static version of Undef.
	 */
	public static final Undef UNDEF = new Undef();

	/**
	 * Return numeric representation of the node's value.
	 * 
	 * @return a <code>Number</code> value
	 */
	@Override
	public final Number toNumber() {

		return Double.NaN;
	}

	/**
	 * Get the object wrapped by this XRef.
	 * 
	 * @return the wrapped value object
	 */
	public final Object getValue() {

		return null;
	}

	/**
	 * The signature for this method is that it should return 0 if the
	 * expressions are equal, a value less than zero if the expression provided
	 * as an argument is greater than the expression, and a value bigger than
	 * zero if the expression provided as argument is less than the expression.
	 * 0 is returned if and only if the expression given as argument is an
	 * instance of Undef or the epxr argument is null.
	 * 
	 * @param expr
	 *            The Expression to compare with
	 * @return the integer value indicating comparison result.
	 */
	@Override
	public final int compareTo(final Expression expr) {

		if (expr instanceof Undef || expr == null) {
			return 0;
		}
		return -1;
	}

	/**
	 * Compares this XObject to the specified object. Only if the argument is
	 * Undef, true will be returned. All else is false.
	 * 
	 * @param obj
	 *            The expression to use in equality check.
	 * @return the boolean representation of this object.
	 */
	@Override
	public final boolean equals(final Object obj) {

		if (!(obj instanceof Expression)) {
			return false;
		}

		if (((Expression) obj).eval() instanceof Undef) {
			return true;
		}

		return false;
	}

	/**
	 * Return the boolean representation of Undefined. This is true iff the
	 * wrapped Number is not null, and is not a representation of zero.
	 * 
	 * @return the <code>boolean</code> representation of this XNumber.
	 */
	@Override
	public final boolean toBoolean() {

		return false;
	}

	/**
	 * Return the string representation of Undef.
	 * 
	 * @return the <code>String</code> representation of Undef.
	 */
	@Override
	public final String toString() {

		return "";
	}

	/**
	 * Return the underlying object. In this case, it is always null.
	 * 
	 * @return null
	 */
	@Override
	public final Object toObject() {

		return null;
	}

}
