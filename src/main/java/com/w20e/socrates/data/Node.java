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

package com.w20e.socrates.data;

/**
 * A <code>Node</code> object holds a<code> Value</code> object and can return
 * one.
 * 
 * @author helmantel
 */
public interface Node {

	/**
	 * Return the (unique) identifier for this node.
	 * 
	 * @return the name for this node
	 */
	String getName();

	/**
	 * Return the value for this node.
	 * 
	 * @return the value for this node.
	 */
	Object getValue();

	/**
	 * Set the value for this node. This can be anything Java-ish.
	 * 
	 * @param obj
	 *            an <code>Object</code> value.
	 */
	void setValue(Object obj);

	/**
	 * Reset to default value.
	 */
	void reset();
}
