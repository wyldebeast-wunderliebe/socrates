/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : DataException.java
 * Classname : DataException
 * Author    : Duco Dokter
 * Date      : 20 Jan 2005
 * Version   : $Revision: 1.6 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dokter DataException is thrown when something concerning the model's
 *         data is wrong.
 */
public class ValidationException extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 6915129923735092707L;

	/**
	 * A cumulative list of errors.
	 */
	private transient final Map<String, Exception> errors;

	/**
	 * Construction time...
	 */
	public ValidationException() {

		super();
		this.errors = new HashMap<String, Exception>();
	}

	/**
	 * Add an error to our cumulative error list.
	 * 
	 * @param string
	 * @param error
	 *            the error to add
	 */
	public final void addError(final String nodeName, final Exception error) {

		this.errors.put(nodeName, error);
	}

	/**
	 * Retrieve the cumulative list of errors.
	 * 
	 * @return a list of errors.
	 */
	public final Map<String, Exception> getErrors() {

		return this.errors;
	}
}
