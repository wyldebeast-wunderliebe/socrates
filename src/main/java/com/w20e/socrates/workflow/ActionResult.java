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
 * File      : ActionResult.java
 * Classname : ActionResult
 * Author    : Duco Dokter
 * Created   : Sat Jan 29 16:04:47 2005
 * Version   : $Revision: 1.4 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.workflow;

/**
 * Specify the result of a ProcessAction.
 */
public interface ActionResult {

	/**
	 * Return string representation of result. This is used in workflow
	 * definitions.
	 * 
	 * @return a <code>String</code> value
	 */
	@Override
	String toString();
}
