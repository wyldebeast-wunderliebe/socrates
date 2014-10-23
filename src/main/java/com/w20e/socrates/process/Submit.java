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
 * File      : Submit.java
 * Classname : Submit
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 13:04:25 2005
 * Version   : $Revision: 1.10 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import com.w20e.socrates.model.Model;
import com.w20e.socrates.workflow.ActionExecException;
import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.Failure;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.Success;
import com.w20e.socrates.submission.HandlerManager;
import com.w20e.socrates.workflow.ProcessContext;

/**
 * The Submit class is a specific instance of the ProcessAction. This class
 * should be able to store the data.
 * 
 * @todo set specific error messages
 */
public class Submit extends AbstractProcessActionImpl {

	/**
	 * Singleton for ok result.
	 */
	private Success ok;

	/**
	 * Creates a new <code>Submit</code> instance.
	 * 
	 * @param id
	 *            a <code>String</code> value
	 */
	public Submit(final String id) {

		super(id);
		this.ok = new Success();
	}

	/**
	 * Store the current data.
	 * 
	 * @throws ActionExecException
	 *             whenever a runtime exception occurs.
	 * @param context
	 *            a <code>ProcessContext</code> value
	 * @return the action result.
	 */
	public final ActionResult exec(final ProcessContext context)
			throws ActionExecException {

		try {
			Model model = ((RunnerContext) context).getModel();

			// Do we need to submit any data at all?
			if (model.getSubmission() != null
					&& model.getSubmission().getAction() != null) {

				// Copy metadata from model into instance
				((RunnerContext) context).getInstance().getMetaData().putAll(model.getMetaData());
				
				HandlerManager.getInstance().submit(
						((RunnerContext) context).getInstance(),
						model,
						model.getSubmission());
			}
		} catch (Exception e) {
			return new Failure(e);
		}

		((RunnerContext) context).getInstance().getMetaData().put("storage-type", "submit");
		
		return this.ok;
	}
}
