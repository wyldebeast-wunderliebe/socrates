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
 * File      : Runner.java
 * Classname : Runner
 * Author    : Duco Dokter
 * Date      : 19 Jan 2005
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.io.Serializable;

import com.w20e.socrates.workflow.Processor;

/**
 * @author dokter
 * 
 *         A Runner is used as a central focus point in a session within
 *         socrates. It can be used to request rendering and store data. The
 *         Runner is a Processor: it supports steps in the process of rendering
 *         pre- and postprocessing, and rendering a questionnaire.
 */
public interface Runner extends Serializable, Processor {
	// Marker interface.
}
