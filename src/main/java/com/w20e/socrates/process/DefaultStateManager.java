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
 * File      : WoliWebStateManager.java
 * Classname : WoliWebStateManager
 * Author    : Wietze Helmantel
 * Date      : 3 feb 2005
 * Version   : $Revision: 1.2 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.rendering.Group;
import com.w20e.socrates.rendering.Page;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.RenderStateImpl;
import com.w20e.socrates.rendering.Renderable;
import com.w20e.socrates.rendering.StateManager;

/**
 * @author D.A.Dokter
 * All groups of layout=page are assumed to be a page. Duh.
 */
public final class DefaultStateManager extends BaseStateManager 
	implements StateManager {

	/**
	 * The index of the <b>next</b> item, -1 if unset or empty model.
	 */
	private int itemIndex = -1;

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(DefaultStateManager.class.getName());

	/**
	 * Holds all possible states.
	 */
	private List<Renderable> states = new ArrayList<Renderable>();

	/**
	 * Enable jump to specific state, by maintaining a mapping based on the id
	 * of the first control in a given state.
	 */
	private List<String> stateIndex = new ArrayList<String>();

	/**
	 * Hold current state.
	 */
	private RenderState currentState;

	/**
	 * Construct a manager by using a reference to a <code>Model</code>.
	 * 
	 * @param cfg
	 *            Configuration for this manager
	 * @param m
	 *            the reference to a Model.
	 * @param inst
	 *            The instance to use.
	 */
	public void init(Configuration cfg, final RenderConfig rCfg, final Model newModel, final Instance inst) {

		this.instance = inst;
		this.model = newModel;
		this.cfg = cfg;

		// Let's create a map of pages... We stuff 'm in a linked map, so as to
		// maintain order, and enable fast determination of the next state.
		//
		for (final Iterator<Renderable> i = rCfg.getItems().iterator(); i.hasNext();) {

			addPages(i.next());			
		}
	}
	
	private void addPages(Renderable item) {

		if (item instanceof Group) {
			if (item instanceof Page) {
				this.states.add(item);
				this.stateIndex.add(item.getId());
			}
		} else {
			for (final Iterator<Renderable> i = ((Group) item).getItems().iterator(); i.hasNext();) {

				addPages(item);
			}
		}
	}

	/**
	 * Reset the manager to the initial state.
	 */
	public void reset() {

		this.itemIndex = -1;
	}

	/**
	 * Calculate the next state, and return it.
	 * 
	 * @see com.w20e.socrates.process.RenderState
	 * @return a a State (essentially being a List) or null if no State found
	 */
	public RenderState next() {

		LOGGER.fine("Asking for next state");

		// create a fresh List to be used as a container for
		// Items that belong to a certain State.
		//
		final List<Renderable> stateItems = new ArrayList<Renderable>();

		// Ok, looks like we're going to have to calculate next state.

		// If we are already at the end, return null.
		if (this.itemIndex >= this.states.size()) {
			return null;
		}

		// We need at least -1...
		if (this.itemIndex < -1) {

			this.itemIndex = -1;
		}

		this.itemIndex++;

		LOGGER.fine("Using with index " + this.itemIndex);

		// Let's find the first state that holds actually items.

		Renderable rItem;
		
		while (this.itemIndex < this.states.size()) {

			rItem = this.states.get(this.itemIndex);

			LOGGER.finer("Item " + rItem.getId() + " found.");

			if (isRelevant(rItem)) {
				stateItems.add(rItem);
				break;
			}

			this.itemIndex++;
		}

		if (stateItems.isEmpty()) {
			// no items recorded for state, return a null State.
			LOGGER.log(Level.WARNING, "Sadly no items were found for next!");

			return null;
		}

		this.currentState = new RenderStateImpl(stateItems.get(0).getId(), stateItems);

		return current();
	}

	/**
	 * Calculate the previous state, and return it.
	 * 
	 * @see com.w20e.socrates.process.RenderState
	 * @return a a State (essentially being a List) or null if no State found
	 */
	public RenderState previous() {

		LOGGER.fine("Asking for previous state");

		// create a fresh List to be used as a container for
		// Items that belong to a certain State.
		//
		final List<Renderable> stateItems = new ArrayList<Renderable>();

		// Ok, looks like we're going to have to calculate next state.

		// If we are already at the beginning, return null.
		if (this.itemIndex < 0) {
			return null;
		}

		// We need at most the size of all possible states.
		if (this.itemIndex > this.states.size()) {

			this.itemIndex = this.states.size();
		}

		this.itemIndex--;

		LOGGER.finer("Using with index " + this.itemIndex);

		// Let's find the first state that holds actually items.

		Renderable rItem;

		while (this.itemIndex > -1) {

			rItem = this.states.get(this.itemIndex);

			LOGGER.finer("Item " + rItem.getId() + " found.");


			if (isRelevant(rItem)) {
				stateItems.add(rItem);
				break;
			}

			// Huh? Is it a bird then? A plane..? Or just irrelevant...
			this.itemIndex--;
		}

		if (stateItems.isEmpty()) {
			// no items recorded for state, return a null State.
			LOGGER.log(Level.WARNING, "Sadly no items were found for previous!");

			return null;
		}

		this.currentState = new RenderStateImpl(stateItems.get(0).getId(), stateItems);

		return current();
	}

	/**
	 * Return the state last calculated by either next or previous. If none
	 * calculated yet, return null.
	 * 
	 * @return the calculated state.
	 */
	public RenderState current() {

		return this.currentState;
	}

	/**
	 * Set the current state to the state given. If not found, return false.
	 * @param stateId
	 * @return
	 */
	public boolean setState(final RenderState state) {

	    final int idx = this.stateIndex.indexOf(((RenderStateImpl) state).getId());

		if (idx != -1) {
			this.itemIndex = idx;
			this.currentState = state;
			return true;
		}
		return false;
	}

	/**
     * Set the current state to the index of state id given minus 1. The minus
     * one part is added so as to be able to end up in the expected state, since
     * servlet will always call next.
     * If not found, return false.
     * @param stateId
     * @return
     */
    public boolean setStateById(final String stateId) {

        int idx = this.stateIndex.indexOf(stateId);

        if (idx != -1) {
            this.itemIndex = idx;
            return true;
        }
        return false;
    }

	/**
	 * Determine whether the statemanager can provide a next state or not.
	 */
	public boolean hasNext() {

		int keepIndex = this.itemIndex;
		RenderState keepState = current();
		boolean hasNext = false;
		
		try {
			if (next().getItems().size() > 0) {
				hasNext = true;
			}
		} catch (Exception e) {
			// nae worries
		}

		this.itemIndex = keepIndex;
		this.currentState = keepState;
		
		return hasNext;
	}

	public boolean hasPrevious() {
		
		int keepIndex = this.itemIndex;
		RenderState keepState = current();
		boolean hasPrevious = false;
		
		try {
			if (previous().getItems().size() > 0) {
				hasPrevious = true;
			}
		} catch (Exception e) {
			// nae worries
		}
		
		this.itemIndex = keepIndex;
		this.currentState = keepState;
		
		return hasPrevious;
	}

    @Override
    /**
     * Get the progress in percentage for this manager. This is rather hard to calculate,
     * since you never know beforehand what will be relevant later on. So we assume an avg.
     * of N states per user, and check this against the real progress.
     */
    public int getProgressPercentage() {

        LOGGER.fine("Size of states: " + this.states.size());
        LOGGER.fine("Current index: " + this.itemIndex);
        
        float stateIndex = this.itemIndex + 1;
        
        int progress = Math.round(100 * (stateIndex / (float) this.states.size()));
        int estimated = Math.round(100 * (stateIndex / 
        		(float) this.cfg.getInt("statemanager.avgstates", this.states.size())));
        
        LOGGER.fine("Real progress: " + progress);
        LOGGER.fine("Estimated progress: " + estimated);
        
        return Math.max(progress, estimated);
    }
}
