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
 * @author dokter Specify the length of the argument. In case the argument is
 *         not a String or Number, check the number of bytes.
 */
public class Length implements Restriction {

	/**
	 * Length to check.
	 */
	private final long len;

	/**
	 * Create new Length restriction.
	 * 
	 * @param length
	 *            length to test against.
	 */
	public Length(final long length) {

		this.len = length;
	}

	/**
	 * Check length of argument.
	 * 
	 * @param str
	 *            string to check.
	 * @return whether the string argument is exactely of length len.
	 */
	@Override
	public final boolean eval(final Object str) {

		if (str.toString().length() == this.len) {
			return true;
		}
		return false;
	}
}
