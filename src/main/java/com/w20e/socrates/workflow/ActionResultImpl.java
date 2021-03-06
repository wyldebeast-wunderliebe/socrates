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

package com.w20e.socrates.workflow;

/**
 * This class implements the basic result of a process action. The status of the
 * result should be used to indicate failure or success, the message can be used
 * to provide either some message for logging purposes, or to provide finer
 * grained control over the result processing for further steps.
 */
public abstract class ActionResultImpl implements ActionResult {

	/**
	 * Constant indicating ok status.
	 */
	public static final String OK = "ok";

	/**
	 * Constant for failing actions.
	 */
	public static final String FAIL = "fail";

	/**
	 * Constant for actions that wait around for user input.
	 */
	public static final String WAIT = "wait";

	/**
	 * Constant for undefined results.
	 */
	public static final String UNDEFINED = "undefined";

	/**
	 * Each result should be representable as string.
	 * 
	 * @return a <code>String</code> value
	 */
	@Override
	public abstract String toString();
}
