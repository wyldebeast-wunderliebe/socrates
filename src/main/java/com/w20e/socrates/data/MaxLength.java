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

package com.w20e.socrates.data;

/**
 * @author dokter Imlementation to determine the maximum length of the argument
 *         for eval.
 */
public class MaxLength implements Restriction {

	/**
	 * What is the maximum length to evaluate against.
	 */
	private final int max;

	/**
	 * Create a new instance, using maxLength to evaluate against.
	 * 
	 * @param maxLength
	 *            max length.
	 */
	public MaxLength(final int maxLength) {

		this.max = maxLength;
	}

	/**
	 * Determine the argument's length, and return wheter it is compliant with
	 * the maximum length for this restriction.
	 * 
	 * @param str
	 *            String to test.
	 * @return whether the length of the argument is smaller than the max length
	 *         specified for this instance.
	 */
	public final boolean eval(final Object str) {

		if (str.toString().length() <= this.max) {
			return true;
		}
		return false;
	}
}
