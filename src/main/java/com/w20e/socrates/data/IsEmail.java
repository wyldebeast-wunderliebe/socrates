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

import com.w20e.socrates.util.EmailValidator;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class IsEmail implements Restriction {

	/**
	 * Check whether this value is an integer.
	 * 
	 * @param value
	 *            object to check.
	 * @return whether integer or not.
	 */
	@Override
	public final boolean eval(final Object value) {

		return EmailValidator.isValid(value.toString());
	}
}
