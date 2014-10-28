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
 * File      : ProcessorImpl.java
 * Classname : ProcessorImpl
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 12:03:42 2005
 * Version   : $Revision: 1.26 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * The processor interface is the core concept in the process package. The
 * interface defines the notion of a complete Process, that is the graph of all
 * states and actions (transitions) between states. The Processor can
 * incorporate complete sub-processors. A process is considered finished when
 * there is no mapping from the current state to another one, given the last
 * result, and the current state is a final state. If the latter is not true,
 * this should be considered an error.
 * 
 * TODO: looks like we use final actions instead of states...
 */
public class ProcessorImpl implements Processor {

	/**
	 * Holder for sole undefined result.
	 */
	private final Undefined undef;

	/**
	 * Container for transformations.
	 */
	private Map<ProcessState, Map<String, ProcessState>> trans;

	/**
	 * Hold initial action id.
	 * 
	 */
	private ProcessAction initAction;

	/**
	 * List of final states.
	 */
	private List<ProcessAction> finalStates;

	/**
	 * Empty constructor for Digester implementation.
	 */
	public ProcessorImpl() {

		this.trans = new HashMap<ProcessState, Map<String, ProcessState>>();
		this.finalStates = new ArrayList<ProcessAction>();
		this.undef = new Undefined();
	}

	/**
	 * Set the state for the initial state.
	 * 
	 * @param init
	 *            Initial action.
	 */
	public final void setInitial(final ProcessAction init) {

		this.initAction = init;
	}

	/**
	 * Get the state for the initial state.
	 * 
	 * @return the initial state.
	 */
	@Override
	public final ProcessAction getInitial() {

		return this.initAction;
	}

	/**
	 * Set the list of final states.
	 * 
	 * @param finals
	 *            list of final states.
	 */
	public final void setFinals(final List<ProcessAction> finals) {

		this.finalStates = finals;
	}

	/**
	 * Get the final states.
	 * 
	 * @return the final states
	 */
	@Override
	public final List<ProcessAction> getFinals() {

		return this.finalStates;
	}

	/**
	 * Execute the next action, or throw an exception when none exists, or the
	 * action throws an exception. The state should remain the same now. This is
	 * implemented by setting the result to Undefined, so that the getNextAction
	 * will return the current action. If you wish to provide other behavior for
	 * exceptions, create a mapping over the Undefined result.
	 * 
	 * @param ctx
	 *            Process context
	 * @exception Exception
	 *                if an error occurs
	 */
	@Override
	public final void next(final ProcessContext ctx) throws ProcessException {

		final ProcessAction action = getNextAction(ctx);

		if (action == null) {
			throw new ProcessException("No next state.");
		}

		ctx.setCurrentAction(action);

		// Execute the action
		try {
			ctx.setResult(action.exec(ctx));
		} catch (ActionExecException e) {
			ctx.setResult(this.undef);
			throw e;
		}
	}

	/**
	 * Determine whether another step can be taken. This is true IFF there is a
	 * mapping from the current state to an action, and if that action exists.
	 * Note: this doesn't mean that the process is actually finished: the state
	 * could be a dead end. Use isFinished to make sure the process has
	 * completed.
	 * 
	 * @param ctx
	 *            process context.
	 * @return a boolean value indicating whether there is a next thing.
	 */
	@Override
	public final boolean hasNext(final ProcessContext ctx) {

		if (getNextAction(ctx) != null) {
			return true;
		}

		return false;
	}

	/**
	 * Determine whether this process has finished. This is true IFF the current
	 * state is a final state, and there is no mapping from this state to
	 * another state in this process, over the last result.
	 * 
	 * @param ctx
	 *            Process context
	 * @return a boolean value indicating whether the process is finished.
	 */
	public final boolean isFinished(final ProcessContext ctx) {

		if (getNextAction(ctx) == null
				&& this.finalStates.contains(ctx.getCurrentAction())) {
			return true;
		}

		return false;
	}

	/**
	 * Create a mapping from state to action. If you create more than one
	 * mapping from one state to an action, the last one added survives.
	 * 
	 * @param newMapping
	 *            the mapping to add
	 */
	public final void addMapping(final Mapping newMapping) {

		// If processor, add all mappings

		if (newMapping.getTo() instanceof Processor) {

			addMapping(new Mapping(newMapping.getFrom(),
					((Processor) newMapping.getTo()).getInitial(), newMapping
							.getCondition()));

			this.trans.putAll(((Processor) newMapping.getTo()).getMappings());

		} else if (newMapping.getFrom() instanceof Processor) {

			for (final Iterator<ProcessAction> i = ((Processor) newMapping
					.getFrom()).getFinals().iterator(); i.hasNext();) {
				addMapping(new Mapping((i.next()), newMapping
						.getTo(), newMapping.getCondition()));
			}
		} else {
			Map<String, ProcessState> mapping;

			if (this.trans.containsKey(newMapping.getFrom())) {
				mapping = this.trans.get(newMapping.getFrom());
			} else {
				mapping = new HashMap<String, ProcessState>();
			}

			mapping.put(newMapping.getCondition(), newMapping.getTo());

			this.trans.put(newMapping.getFrom(), mapping);
		}
	}

	// @TODO Implement this for convenience
	// public boolean hasMapping(ProcessAction from, ProcessAction to, String
	// condition) {
	//    
	// return this.trans.containsKey(key)
	// }

	/**
	 * Get the next action to execute, or null if none available. Do NOT update
	 * current action!
	 * 
	 * @param ctx
	 *            process context to use.
	 * @return a <code>ProcessAction</code> or null
	 */
	private ProcessAction getNextAction(final ProcessContext ctx) {

		// We haven't started yet...
		if (ctx.getCurrentAction() == null) {

			if (this.initAction != null) {

				return this.initAction;
			}
		} else {
			// If the last result is undefined, return current action.
			if (ActionResultImpl.UNDEFINED.equals(ctx.getResult().toString())
					&& !(this.trans.get(ctx.getCurrentAction()))
							.containsKey(ctx.getResult().toString())) {
				return ctx.getCurrentAction();
			}

			try {
				return (ProcessAction) (this.trans.get(ctx.getCurrentAction()))
						.get(ctx.getResult().toString());
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

	/**
	 * Get the mappings for this processor.
	 * 
	 * @return a <code>Map</code> value
	 */
	@Override
	public final Map<ProcessState, Map<String, ProcessState>> getMappings() {

		return this.trans;
	}

	/**
	 * Set the mappings for this processor.
	 * 
	 * @param map
	 *            the map to use for mappings.
	 */
	public final void setMappings(
			final Map<ProcessState, Map<String, ProcessState>> map) {

		this.trans = map;
	}

	/**
	 * Add all mappings in the map.
	 * 
	 * @param map
	 *            mappings.
	 */
	public final void addMappings(
			final Map<ProcessState, Map<String, ProcessState>> map) {

		this.trans.putAll(map);
	}
}
