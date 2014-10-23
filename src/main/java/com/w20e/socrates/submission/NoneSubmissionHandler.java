/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.submission;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.Submission;


/**
 * Empty submission handler: nothing is submitted.
 * @author dokter
 */
public class NoneSubmissionHandler implements SubmissionHandler {

  /**
   * Do nothing.
   * @param data instance data
   * @param submission Submission implementation to use.
   * @throws SubmissionException should never happen :)
   */
  public final void submit(final Instance data, final Model model, final Submission submission)
  throws SubmissionException {
	  // Do nothing, as the name might have suggested...
  }
}
