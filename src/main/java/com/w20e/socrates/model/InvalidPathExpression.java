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

package com.w20e.socrates.model;

/**
 * Thrown when a the path expression used to question an Instance is invalid.
 * 
 * @author W.G.Helmantel
 */
public class InvalidPathExpression extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 2663264015693477922L;

	/**
	 * Create an InvalidPathExpression exception with an interesting message of
	 * some kind.
	 * 
	 * @param message
	 *            some cognitive message :-)
	 */
	public InvalidPathExpression(final String message) {
		super(message);
	}
}
