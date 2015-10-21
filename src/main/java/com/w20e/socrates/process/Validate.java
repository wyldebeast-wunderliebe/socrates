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
 * File      : Validate.java
 * Classname : Validate
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 13:04:25 2005
 * Version   : $Revision: 1.29 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.StateManager;
import com.w20e.socrates.workflow.ActionExecException;
import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.Failure;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.ProcessContext;
import com.w20e.socrates.workflow.Success;

/**
 * The Validate class is a specific instance of the ProcessAction. This type of
 * action validates input from context, and sets data to the model if possible.
 * 
 * @todo set specific error messages
 * @todo rethink name...
 */
public class Validate extends AbstractProcessActionImpl {

	/**
	 * Reuse ok result.
	 */
	private Success ok;

	/**
	 * Creates a new <code>Validate</code> instance.
	 * 
	 * @param id
	 *            a <code>String</code> value
	 */
	public Validate(final String id) {

		super(id);
		this.ok = new Success();
	}

	/**
	 * Validate the current data against the model, and set if possible.
	 * 
	 * @throws ActionExecException
	 *             whenever a runtime exception occurs.
	 * @param context
	 *            a <code>ProcessContext</code> value
	 * @return the action result.
	 */
	@Override
	public final ActionResult exec(final ProcessContext context)
			throws ActionExecException {

		StateManager mgr = ((RunnerContext) context).getStateManager();
		RenderState state = mgr.current();

		// No worries, nothing to validate.
		if (state == null) {
			return this.ok;
		}

		// If the user is going back, skip data setting and validation
		if ("true".equals(context.getProperty("previous", "false"))) {
		    context.setProperty("previous", "false");
		    return this.ok;
		}
		
		try {
			DataHandler.setData(
					((RunnerContextImpl) context).getData(),
					((RunnerContext) context).getModel(),
					((RunnerContext) context).getInstance(), state,
					((RunnerContext) context).getLocale());
		} catch (ValidationException e) {

			return new Failure(e);
		}

		return this.ok;
	}

}
