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

package com.w20e.socrates.submission;

/**
 * Thrown when the SubmissionHanlder cannot handle (is: submit) the data.
 * Calling applications should decide what to do in this case: use another
 * handler, try again, etc.
 * 
 * @author D.A.Dokter
 */
public final class SubmissionException extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -1603828301343076090L;

	/**
	 * Creates a new <code>SubmissionException</code> instance.
	 * 
	 * @param msg
	 *            Message for this exception.
	 */
	public SubmissionException(final String msg) {

		super(msg);
	}

	/**
	 * Creates a new <code>SubmissionException</code> instance.
	 */
	public SubmissionException() {

		super();
	}
}
