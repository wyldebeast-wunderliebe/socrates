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

/**
 * Group of RenderItem objects. With a group you can for instance create more
 * elaborate layouts on screen, or on paper.
 * 
 * @author dokter
 * 
 */
public interface Group extends Renderable, RenderableContainer {
	// Marker interface.

	/**
	 * Label for the group. Should contain short description.
	 */
	Label getLabel();

	/**
	 * Hint for this group. Should hold extra info.
	 * 
	 * @return
	 */
	String getHint();

	/**
	 * How should individual items of this group be shown?
	 */
	String getLayout();
}
