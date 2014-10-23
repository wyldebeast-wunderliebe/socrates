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
 * File      : HandlerManager.java
 * Classname : HandlerManager
 * Author    : Duco Dokter
 * Created   : Wed Jan 26 16:19:14 2005
 * Version   : $Revision: 1.12 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.submission;

import java.util.Map;
import java.util.HashMap;

import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.Submission;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.factories.UnsupportedProtocolException;

/**
 * Handler manager for submissions. This manager may hold several specific
 * handlers for protocols.
 */
public final class HandlerManager {

	/**
	 * Submit registry.
	 */
	private final Map<String, SubmissionHandler> registry;

	/**
	 * Holder of singleton instance.
	 */
	private static HandlerManager manager = null;

	/**
	 * Declare constructor as private.
	 */
	private HandlerManager() {

		this.registry = new HashMap<String, SubmissionHandler>();
	}

	/**
	 * Return the single instance of the resource.
	 * 
	 * @return a <code>HandlerManager</code> value
	 */
	public static HandlerManager getInstance() {

		synchronized (HandlerManager.class) {
			if (manager == null) {
				manager = new HandlerManager();
			}
		}
		return manager;
	}

	/**
	 * Submit data, using the submission given.
	 * 
	 * @param data
	 *            Instance data
	 * @param model 
	 * @param submission
	 *            Submission implementation
	 * @throws SubmissionException
	 *             when the data can't be submitted
	 * @throws UnsupportedProtocolException
	 *             when there is no handler for the specified protocol.
	 */
	public void submit(final Instance data, Model model, final Submission submission)
			throws SubmissionException, UnsupportedProtocolException {

		final String proto = submission.getAction().getScheme();

		if (!this.registry.containsKey(proto)) {
			throw new UnsupportedProtocolException("No handler for " + proto);
		}

		final SubmissionHandler handler = this.registry.get(proto);

		handler.submit(data, model, submission);
	}

	/**
	 * Register a new handler for this protocol.
	 * 
	 * @param proto
	 *            a <code>String</code> value
	 * @param handler
	 *            a <code>SubmissionHandler</code> value
	 */
	public void register(final String proto, final SubmissionHandler handler) {

		this.registry.put(proto, handler);
	}
}
