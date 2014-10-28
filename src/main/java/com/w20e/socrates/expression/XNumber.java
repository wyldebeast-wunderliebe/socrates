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
 * File      : XNumber.java
 * Classname : XNumber
 * Author    : Duco Dokter
 * Date      : 14 Jan 2005
 * Version   : $Id: XNumber.java,v 1.19 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;


/**
 * Wrapper class for numeric operands.
 */
public class XNumber extends AbstractXObjectImpl {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Wrapped number for this object.
	 */
	private final Number num;

	/**
	 * Creates a new <code>XNumber</code> instance, wrapping the
	 * <code>Number</code> provided as argument.
	 * 
	 * @param number
	 *            a <code>Number</code> value.
	 */
	public XNumber(final Number number) {

		super();
		this.num = number;
	}

	/**
	 * Get the Number obect wrapped by this XNumber.
	 * 
	 * @return the wrapped <code>Number</code> object
	 */
	@Override
	public final Number toNumber() {

		return Double.valueOf(this.num.doubleValue());
	}

	/**
	 * The signature for this method is that it should return 0 if the
	 * expressions are equal, a value less than zero if the expression provided
	 * as an argument is bigger than the expression, and a value bigger than
	 * zero if the expression provided as argument is smaller than the expression.
	 * 
	 * @param expr
	 *            the expresssion to compare to.
	 * @return the integer value indicating comparison result.
	 */
	@Override
	public final int compareTo(final Expression expr) {
	    
	    if (this.num == null) {
	        
	        if (expr == null || expr.eval() instanceof Undef) {
	            return 0;
	        } else {
	            
	            return -1;
	        }
	    }

		if (expr == null || expr.eval() instanceof Undef) {
			return 1;
		}

		return Double.compare(this.num.doubleValue(), expr.eval().toNumber().doubleValue());		    
	}

	/**
	 * Compares this XObject to the specified object. If the argument is null,
	 * false will be returned. If the argument is an XBoolean, both will
	 * converted to boolean, otherwise the comparison will be numeric.
	 * 
	 * @param obj
	 *            The expression to use in equality check.
	 * @return the boolean representation of this object.
	 */
	@Override
	public final boolean equals(final Object obj) {

	    if (! (obj instanceof Expression)) {
	        return false;
	    }

	    return this.compareTo((Expression) obj) == 0;
	}

	/**
	 * Return the boolean representation of this XNumber. This is true iff the
	 * wrapped Number is not null, and is not a representation of zero.
	 * 
	 * @return the <code>boolean</code> representation of this XNumber.
	 */
	@Override
	public final boolean toBoolean() {

		if (this.num == null || this.num.floatValue() == 0.0) {

			return false;
		}

		return true;
	}

	/**
	 * Return the string representation of this XNumber.
	 * 
	 * @return the <code>String</code> representation of this XNumber.
	 */
	@Override
	public final String toString() {

		if (this.num == null) {
			return String.valueOf(Float.NaN);
		}
		return this.num.toString();
	}

	/**
	 * Return underlying object.
	 * 
	 * @return Number object wrapped by this XObject.
	 */
	@Override
	public final Object toObject() {

		return this.num;
	}

}
