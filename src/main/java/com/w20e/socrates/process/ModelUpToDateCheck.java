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

package com.w20e.socrates.process;

import java.net.URI;


/**
 * Check 'up to date'-ness for a given model.
 * @author dokter
 */
public interface ModelUpToDateCheck {

  /**
   * Check whether the model identified by id is up to date.
   * @param id model's id
   * @return whether up to date or not.
   */
  boolean isUpToDate(URI id);
}
