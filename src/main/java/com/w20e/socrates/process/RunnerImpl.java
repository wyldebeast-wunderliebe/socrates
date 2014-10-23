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
 * File      : RunnerImpl.java
 * Classname : RunnerImpl
 * Author    : Duco Dokter
 * Date      : 19 Jan 2005
 * Version   : $Revision: 1.36 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.net.URL;
import java.util.Date;

import com.w20e.socrates.process.Runner;
import com.w20e.socrates.workflow.ProcessorImpl;

/**
 * @author dokter
 * @todo what if configure fails?
 *
 * The Runner implementation is the core concept in running a
 * questionnaire or form. The runner takes care of the complete
 * workflow.
 */
public class RunnerImpl
  extends ProcessorImpl
  implements Runner {

  /**
   * UID.
   */
  private static final long serialVersionUID = -3405032371464860960L;

  /**
   * Holds the identifier for rendering the model withing the
   * workflow.
   */
  public static final String RENDER = "render";

  /**
   * Upon runner creation, store its creation time.
   */
  private Date creationTime;

  /**
   * Creates a new <code>RunnerImpl</code> instance. The runner will
   * configure itself based on the configuration fetched from
   * <code>ConfigurationResource</code>.
   *
   * @param id Runner's config URL
   * @param model the model to use
   */
  public RunnerImpl(final URL id) {

    super();
    init(id);
    this.creationTime = new Date();
  }

  /**
   * Initialize the runner. This includes getting the preprocessing
   * stuff in place.
   *
   * @param id Runner's config URL
   * @param model the model to use
   */
  public final void init(final URL id) {

    // First, setup the runner's configurator. This is actually a
    // ProcessorFactory.
    RunnerConfigurator configurator = new RunnerConfigurator(this);

    if (id != null) {
      try {
        configurator.configure(id);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Fetch Runner's creation time.
   * @return this object's creation as Date.
   */
  public final Date getCreationTime() {
    return this.creationTime;
  }
}
