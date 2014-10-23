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
 * File      : SubmissionHandler.java
 * Classname : SubmissionHandler
 * Author    : Duco Dokter
 * Created   : Wed Jan 26 16:19:14 2005
 * Version   : $Revision: 1.10 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.submission;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.Submission;

/**
 * Handler for submissions given a specific protocol. SubmissionHandler
 * instances are registered with the HandlerManager.
 */
public interface SubmissionHandler {

	/**
	 * Submit data to some specific storage.
	 * 
	 * @param data
	 *            Instance data to submit.
	 * @param model 
	 * @param submission
	 *            Submission
	 * @throws SubmissionException
	 *             when something goes wrong...
	 */
	void submit(final Instance data, Model model, final Submission submission)
			throws SubmissionException;

}
