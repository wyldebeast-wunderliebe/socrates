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
 * File      : State.java
 * Classname : State
 * Author    : Duco Dokter
 * Date      : 19 Jan 2005
 * Version   : $Revision: 1.13 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.rendering;

import java.io.Serializable;
import java.util.Collection;

/**
 * State stores some moment in time. The RenderState should hold a list of
 * RenderItems, that can either be groups, controls, or a combination of both.
 * 
 * @author dokter
 */
public interface RenderState extends Serializable {

    /**
     * A state really should have an id.
     * @return
     */
    String getId();

	/**
	 * Get the items in this state.
	 * 
	 * @return the list of items in this state.
	 */
	Collection<Renderable> getItems();
}
