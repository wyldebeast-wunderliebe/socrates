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
 * File      : ActionExecException.java
 * Classname : ActionExecException
 * Author    : Duco Dokter
 * Created   : Wed Feb  2 13:33:42 2005
 * Version   : $Revision: 1.7 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

/**
 * This exception is thrown whenever an anomaly occurs in executing a
 * ProcessAction. Normally, an ActionResult should be used to indicate the
 * action's result.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class ActionExecException extends ProcessException {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1198017510560112839L;

	/**
	 * Creates a new <code>ActionExecException</code> instance.
	 */
	public ActionExecException() {
		super();
	}

	/**
	 * Creates a new <code>ActionExecException</code> instance.
	 * 
	 * @param message
	 *            a <code>String</code> value
	 */
	public ActionExecException(final String message) {

		super(message);
	}
}
