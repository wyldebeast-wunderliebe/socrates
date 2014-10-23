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
 * File      : Mapping.java
 * Classname : Mapping
 * Author    : Duco Dokter
 * Created   : Wed Feb  2 14:24:54 2005
 * Version   : $Revision: 1.6 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.workflow;

/**
 * Mapping class for defining mappings between actions within a process. The
 * mapping maps between two ProcessState objects (ProcessAction or Processor)
 * based on the result of the 'from' action.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class Mapping {

	/**
	 * id of the 'from' action.
	 */
	private final ProcessState from;

	/**
	 * id of the 'to' action.
	 */
	private final ProcessState to;

	/**
	 * string representation of the result.
	 */
	private final String condition;

	/**
	 * Creates a new <code>Mapping</code> instance.
	 * 
	 * @param fromState
	 *            map from this state
	 * @param toState
	 *            map to this state
	 * @param cond
	 *            map over this result.
	 */
	public Mapping(final ProcessState fromState, final ProcessState toState,
			final String cond) {

		this.from = fromState;
		this.to = toState;
		this.condition = cond;
	}

	/**
	 * Return the 'from' id.
	 * 
	 * @return a <code>String</code> value
	 */
	public final ProcessState getFrom() {

		return this.from;
	}

	/**
	 * Return the 'to' id.
	 * 
	 * @return a <code>String</code> value
	 */
	public final ProcessState getTo() {

		return this.to;
	}

	/**
	 * Return the 'condition' for the transformation.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getCondition() {

		return this.condition;
	}
}
