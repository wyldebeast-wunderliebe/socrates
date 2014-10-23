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
 * @author dokter Rather unrestricted type.
 */
public class XSString extends DataTypeImpl {

	/**
	 * Construct new XSString object.
	 */
	public XSString() {
		super();
		addTransformation(new ToString());
	}
}
