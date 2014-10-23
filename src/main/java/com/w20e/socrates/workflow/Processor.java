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
 * File      : Processor.java
 * Classname : Processor
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 10:15:21 2005
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.util.List;
import java.util.Map;

/**
 * The processor interface is the core concept in the workflow package. The
 * interface defines the notion of a complete Process, that is the graph of all
 * states (or rather: actions) and transitions over a state's result between
 * states. The processor will start processing actions with the initial action,
 * and proceed untill no more mappings exist from one action to the next. The
 * processor will be in a final state whenever the current action (state) is in
 * the list of final states. It is up to the implementation of the processor to
 * ensure that every action has a unique id: no two states with the same name
 * can exist in a FSA.
 * 
 * The Processor interface supports the flyweight pattern, by defining all state
 * dependent methods to take the context as argument.
 */
public interface Processor extends ProcessState {

	/**
	 * Execute next step (Action). An exception is thrown when no more action is
	 * available, or when an action throws an exception. The processor must
	 * remain in the same state.
	 * 
	 * @param ctx
	 *            process context
	 * @exception Exception
	 *                if an error occurs
	 */
	void next(ProcessContext ctx) throws ProcessException;

	/**
	 * Determine whether a next step is available. This is true if and only if
	 * there is a mapping from the last action (state) executed over the result
	 * of that action to a new state, and that new state exists.
	 * 
	 * @param ctx
	 *            the process context
	 * @return boolean value indicating whether there is a next state.
	 */
	boolean hasNext(ProcessContext ctx);

	/**
	 * Return all mappings for this processor.
	 * 
	 * @return a <code>Map</code> value
	 */
	Map<ProcessState, Map<String, ProcessState>> getMappings();

	/**
	 * Return initial state for this processor.
	 * 
	 * @return a <code>ProcessAction</code> value
	 */
	ProcessAction getInitial();

	/**
	 * Get the list of final states.
	 * 
	 * @return a <code>List</code> value
	 */
	List<ProcessAction> getFinals();
}
