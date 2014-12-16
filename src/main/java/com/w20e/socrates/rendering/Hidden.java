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

package com.w20e.socrates.rendering;

public class Hidden extends Input {

	/**
	 * UID.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Construct hidden type. Useful for providing extra info that is rendered
	 * invisible.
	 */
	public Hidden(String id) {
		super(id);
		setType("hidden");
	}
}
