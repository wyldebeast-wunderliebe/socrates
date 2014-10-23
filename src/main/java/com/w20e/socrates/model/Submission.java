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
 * File      : Submission.java
 * Classname : Submission
 * Author    : Duco Dokter
 * Created   : Thu Jan 27 14:57:07 2005
 * Version   : $Revision: 1.7 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.net.URI;

/**
 * Interface defining the Submission properties within XForms. For the action
 * URL we sadly have resorted to a String, since the java URL cannot handle more
 * interesting types like, jdbc or xmldb...
 */
public interface Submission {

	/**
	 * Get the method for this submission.
	 * 
	 * @return a <code>String</code> value
	 */
	String getMethod();

	/**
	 * Get the Id for this submission.
	 * 
	 * @return a <code>String</code> value
	 */
	String getId();

	/**
	 * Get the action for this submission.
	 * 
	 * @return a URI string identifying the submit medium.
	 */
	URI getAction();

	/**
	 * Set the action.
	 * 
	 * @param uri
	 *            action URI.
	 */
	void setAction(final URI uri);
}
