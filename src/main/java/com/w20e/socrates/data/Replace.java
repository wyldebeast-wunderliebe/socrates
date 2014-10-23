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

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Pattern based replacement operation.
 * 
 * @author dokter
 */
public class Replace implements Transformation {

	/**
	 * Pattern.
	 */
	private final Pattern pat;

	/**
	 * replacement.
	 */
	private final String str;

	/**
	 * Create a new replace object. Make VERY SURE you actually provide the
	 * pattern correctly. When you mean a literal '.' for instance, the pattern
	 * should say '\\.' to escape the escape!!! Yes, Java is not Perl...
	 * 
	 * @param newPat
	 *            original pattern
	 * @param newStr
	 *            replacement string
	 */
	public Replace(final String newPat, final String newStr) {

		this.pat = Pattern.compile(newPat);
		this.str = newStr;
	}

	/**
	 * Transform given object by applying the constructor pattern.
	 * 
	 * @param obj
	 *            string to use in replacement
	 * @return new string.
	 */
	public final Object transform(final Object obj) {

		return this.pat.matcher(obj.toString()).replaceAll(this.str);
	}

	/**
	 * Transform given object by applying the constructor pattern.
	 * 
	 * @param obj
	 *            string to use in replacement
	 * @return new string.
	 */
	public final Object transform(final Object obj, final Locale l) {

		return this.transform(obj);
	}

}
