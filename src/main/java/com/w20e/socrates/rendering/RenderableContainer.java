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

package com.w20e.socrates.rendering;

import java.util.List;

/**
 * Group of RenderItem objects. With a group you can for instance create more
 * elaborate layouts on screen, or on paper.
 * 
 * @author dokter
 * 
 */
public interface RenderableContainer {

	/**
	 * Get all RenderItem objects for this configuration.
	 * 
	 * @return collection of renderable items.
	 */
	List<Renderable> getItems();

}
