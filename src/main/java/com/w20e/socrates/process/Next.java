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
 * File      : Next.java
 * Classname : Next
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 13:04:25 2005
 * Version   : $Revision: 1.15 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import com.w20e.socrates.workflow.ActionExecException;
import com.w20e.socrates.workflow.Failure;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.ProcessContext;
import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.Success;
import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.StateManager;


/**
 * The Next class is a specific instance of the ProcessAction. This
 * action just changes the new state of the running questionnaire.
 */
public class Next
  extends AbstractProcessActionImpl {

  /**
   * Singleton for success.
   */
  private Success ok;

  /**
   * Creates a new <code>Next</code> instance.
   *
   * @param id the unique id for this action.
   */
  public Next(final String id) {

    super(id);
    this.ok = new Success();
  }

  /**
   * Create the next state for the process. If no next state is available,
   * the result for this action will be a <code>Failure</code>. This enables
   * a processor to determine finishing steps for a model accordingly.
   *
   * @param context a <code>ProcessContext</code> value
   * @throws ActionExecException if an error occurs
   * @return Success in case a next state could be generated, Failure
   * otherwise.
   */
  public final ActionResult exec(final ProcessContext context)
    throws ActionExecException {

    //try {
      StateManager mgr = ((RunnerContext) context).getStateManager();
      RenderState state = mgr.next();

      // No next state available, so let's call it a quits.
      if (state == null || state.getItems().size() == 0) {
        return new Failure();
      }
    //} catch (Exception e) {
    //  throw new ActionExecException(e.getMessage());
    //}

    return this.ok;
  }
}
