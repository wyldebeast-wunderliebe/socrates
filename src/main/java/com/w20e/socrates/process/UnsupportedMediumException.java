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
 * File      : UnsupportedMediumException.java
 * Classname :
 * Author    : Duco Dokter
 * Date      :
 * Version   :
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

/**
 * @author helmantel
 * 
 *         Checked exception to be thrown when the medium requested is not
 *         supported in socrates or an implemenation
 */
public class UnsupportedMediumException extends Exception {

    /**
     * Constructor taking message as argument.
     * @param message
     */
    public UnsupportedMediumException(final String message) {
        super(message);
    }
    
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1962923370912464889L;

}
