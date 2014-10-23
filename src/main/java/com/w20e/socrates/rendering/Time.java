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


/**
 * Control for date input. Date format defaults to dd/MM/yyyy. See SimpleDateFormat
 * for possible formats.
 * @author dokter
 *
 */
public class Time extends Date {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construct input type.
	 */
	public Time(String id) {
		super(id);
		setType("time");
		setProperty("format", "hh:mm");
	}

}
