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
 * File      : Success.java
 * Classname : Success
 * Author    : Duco Dokter
 * Created   : Wed Feb  2 13:49:25 2005
 * Version   : $Revision: 1.5 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.workflow;

/**
 * This class implements the basic result of a process action. The
 * status of the result should be used to indicate failure or success,
 * the message can be used to provide either some message for logging
 * purposes, or to provide finer grained control over the result
 * processing for further steps.
 */
public class Success extends ActionResultImpl {

  /**
   * Return the string representation of this result.
   *
   * @see ActionResultImpl.OK
   * @return a <code>String</code> value
   */
  @Override
public final String toString() {

    return ActionResultImpl.OK;
  }
}
