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
 * File      : RunnerFactory.java
 * Classname : RunnerFactory
 * Author    : Wietze Helmantel
 * Date      : 3 feb 2005
 * Version   : $Revision: 1.6 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.net.URI;


/**
 * @author helmantel
 * 
 *         <code>RunnerFactory</code> delivers Runner classes.
 */
public interface RunnerFactory {

	/**
	 * Create a Runner given the id, locale and medium.
	 * 
	 * @param url
	 *            Unique id for a runner
	 * @return a Runner instance
	 * @throws UnsupportedMediumException
	 *             when the medium asked is not supported.
	 */
	Runner createRunner(URI uri) throws UnsupportedMediumException;
}
