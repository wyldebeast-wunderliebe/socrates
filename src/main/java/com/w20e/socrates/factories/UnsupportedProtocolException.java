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

package com.w20e.socrates.factories;

/**
 * Thrown when a protocol requested is not supported.
 * 
 * @author W.G.Helmantel
 */
public class UnsupportedProtocolException extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 201741762466137512L;

	/**
	 * Given Protocol is not supported.
	 */
	public UnsupportedProtocolException() {
		super();
	}

	/**
	 * Given Protocol is not supported.
	 * 
	 * @param message
	 *            some cognitive message.
	 */
	public UnsupportedProtocolException(final String message) {
		super(message);
	}
}
