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
 * File      : StateManager.java
 * Classname : StateManager
 * Author    : Wietze Helmantel
 * Date      : 3 feb 2005
 * Version   : $Revision: 1.12 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.rendering;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.Model;

/**
 * @author helmantel
 * 
 *         The StateManager holds the history in terms of State objects of a
 *         questionnaire, and can deliver new States. A state is a snapshot of
 *         the running model, that is a step in processing a complete model. The
 *         simplest state is with a runner that processes a complete model in
 *         one go (think: electronic forms). More complex states are involved
 *         where a runner needs to render one or more items of a questionnaire
 *         per step. The StateManager is responsible for delivering valid
 *         states: if another process gets a previous state, and changes it, the
 *         statemanager must determine whether following states are still valid.
 */
public interface StateManager {

	/**
	 * We need this for the while.
	 * @param cfg 
	 * 
	 * @param cfg
	 *            Render config
	 */
	void init(Configuration cfg, RenderConfig rCfg, Model model, Instance instance);

	/**
	 * Return the current state.
	 * 
	 * @return a <code>State</code> or null in case of an error or none found.
	 */
	RenderState current();

	/**
	 * Determine the next state, and return it. If no next state could be
	 * created, null should be returned.
	 * 
	 * @return a <code>State</code> or null in case of an error or none found.
	 */
	RenderState next();

	/**
	 * Determine whether there actually is a next state.
	 */
	boolean hasNext();

	/**
	 * Return the previous state.
	 * 
	 * @return a <code>State</code> or null in case of an error or none found.
	 */
	RenderState previous();

	/**
	 * Determine whether there actually is a previous state.
	 */
	boolean hasPrevious();

	/**
	 * Set the current state to the state given. This should make the state
	 * manager reset all to that state, and proceed from there. If the given
	 * state doesn't exist, false is returned and nothing is done.
	 */
	boolean setState(RenderState state);

    /**
     * Set the current state to the state given y it's id. This should make the state
     * manager reset all to that state, and proceed from there. If the given
     * state doesn't exist, false is returned and nothing is done.
     */
    boolean setStateById(String stateId);

    /**
     * Where are we in the questionnaire?
     */
    int getProgressPercentage();
}
