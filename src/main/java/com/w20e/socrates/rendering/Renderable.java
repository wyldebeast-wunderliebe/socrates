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
 * Base renderable item. This is a (very) abstract notion of what can be exposed
 * in a form/questionnaire.
 * 
 * @author dokter
 * 
 */

public interface Renderable {

	/**
	 * Get the type of the stuff to render.
	 */
	String getType();

	/**
	 * All renderable items should have a unique id.
	 */
	String getId();

	/**
	 * All renderables may have properties, used for rendering.
	 */
	String getProperty(String name, String defaultValue);
}
