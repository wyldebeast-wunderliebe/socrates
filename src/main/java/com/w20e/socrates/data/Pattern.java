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
 * @author dokter This restriction can be used to match any string against a
 *         regular expression.
 */
public class Pattern implements Restriction {

	/**
	 * Regular expression.
	 */
	private final java.util.regex.Pattern pat;

	/**
	 * Create a new Pattern restriction, using <code>pattern</code> as regular
	 * expression.
	 * 
	 * @param pattern
	 *            the regular expression to use.
	 */
	public Pattern(final String pattern) {

		this.pat = java.util.regex.Pattern.compile(pattern);
	}

	/**
	 * Evaluate the argument against this object's pattern.
	 * 
	 * @param str
	 *            string to match.
	 * @return whether str matches the regular expression.
	 */
	@Override
	public final boolean eval(final Object str) {

		if (this.pat.matcher((String) str).matches()) {
			return true;
		}
		return false;
	}
}
