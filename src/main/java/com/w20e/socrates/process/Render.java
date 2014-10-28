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
 * File      : Render.java
 * Classname : Render
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 13:04:25 2005
 * Version   : $Revision: 1.21 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.io.OutputStream;

import com.w20e.socrates.formatting.Formatter;
import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.Renderable;
import com.w20e.socrates.rendering.StateManager;
import com.w20e.socrates.workflow.ActionExecException;
import com.w20e.socrates.workflow.ActionResult;
import com.w20e.socrates.workflow.Failure;
import com.w20e.socrates.workflow.AbstractProcessActionImpl;
import com.w20e.socrates.workflow.ProcessContext;
import com.w20e.socrates.workflow.Wait;

/**
 * The Render class is a specific instance of the ProcessAction, used
 * for rendering data to some output stream.
 */
public class Render
  extends AbstractProcessActionImpl {

  /**
   * Hold wait ref.
   */
  private Wait wait;

  /**
   * Creates a new <code>Render</code> instance.
   *
   * @param id a <code>String</code> value
   */
  public Render(final String id) {

    super(id);
    this.wait = new Wait();
  }

  /**
   * Render things, given the formatter in the context. The rendering
   * should thrown an exception in case something goes wrong; this
   * action should never return a failure as result.
   *
   * @throws ActionExecException whenever something really goes
   * wrong...
   * @param context a <code>ProcessContext</code> value
   * @return the result of the rendering action
   */
  @Override
public final ActionResult exec(final ProcessContext context)
    throws ActionExecException {

    try {
      OutputStream out = ((RunnerContext) context).getOutputStream();
      StateManager mgr = ((RunnerContext) context).getStateManager();
      Formatter fmt = ((RunnerContext) context).getFormatter();

      RenderState state = mgr.current();
      
      // Check whether we actually have anything to render...
      // This is the case if anything is not hidden.
      boolean render = false;
      
      for (Renderable r: state.getItems()) {
          if ((!r.getType().equals("hidden")) && (!r.getType().equals("hiddengroup"))) {
              render = true;
              break;
          }
      }
      
      if (!render) {
          return new Failure(new Exception("No visible items"));
      }

      // Send the stuff to the outputstream given.
      fmt.format(state.getItems(), out, (RunnerContext) context);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ActionExecException(e.getMessage());
    }

    return this.wait;
  }
}
