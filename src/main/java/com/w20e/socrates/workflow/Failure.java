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
 * File      : Failure.java
 * Classname : Failure
 * Author    : Duco Dokter
 * Created   : Wed Feb  2 13:49:01 2005
 * Version   : $Revision: 1.7 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

/**
 * This class implements the basic result of a process action. The status of the
 * result should be used to indicate failure or success, the message can be used
 * to provide either some message for logging purposes, or to provide finer
 * grained control over the result processing for further steps.
 */
public class Failure extends ActionResultImpl {

	/**
	 * Wrapped exception.
	 */
	private final Exception exc;

	/**
	 * Creates a new <code>Failure</code> instance.
	 */
	public Failure() {
		super();
		this.exc = null;
	}

	/**
	 * Constructor taking an exception.
	 * 
	 * @param e
	 *            an <code>Exception</code> value
	 */
	public Failure(final Exception e) {

		super();
		this.exc = e;
	}

	/**
	 * Return the string representation of this result.
	 * 
	 * @see ActionResultImpl.FAIL
	 * @return a <code>String</code> value
	 */
	@Override
	public final String toString() {

		return ActionResultImpl.FAIL;
	}

	/**
	 * Return the wrapped exception.
	 * 
	 * @return the wrapped exception, or null if none.
	 */
	public final Exception getException() {

		return this.exc;
	}
}
