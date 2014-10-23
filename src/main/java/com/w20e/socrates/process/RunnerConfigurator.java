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
 * File      : RunnerConfigurator.java
 * Classname : RunnerConfigurator
 * Author    : Duco Dokter
 * Created   : Wed Feb  2 13:30:43 2005
 * Version   : $Revision: 1.12 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.process;

import java.net.URL;

import com.w20e.socrates.process.Runner;
import com.w20e.socrates.workflow.ProcessorFactory;
import com.w20e.socrates.workflow.ProcessorImpl;

/**
 * Configurator for the Runner.
 *
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class RunnerConfigurator
  extends ProcessorFactory {

  /**
   * Creates a new <code>RunnerConfigurator</code> instance.
   *
   * @param runner a <code>Runner</code> value
   */
  public RunnerConfigurator(final Runner runner) {

    super((ProcessorImpl) runner);
  }

  /**
   * Configure the runner with this URL.
   *
   * @param url an <code>URL</code> value
   * @exception Exception if an error occurs
   */
  public final void configure(final URL url)
    throws Exception {

    createProcessor(url);
  }
}
