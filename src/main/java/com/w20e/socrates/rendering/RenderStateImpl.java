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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implement the notion of a page for Socrates Rendering. A Page holds render
 * items, that may be grouped.
 * 
 * @author dokter
 * 
 */
public class RenderStateImpl implements RenderState {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Hold RenderItem objects.
	 */
	private Collection<Renderable> items;

	private String id;

	
	public RenderStateImpl(final String newId) {
	        
	    this.id = newId;
	    this.items = new ArrayList<Renderable>();
	}

	public RenderStateImpl(final String newId, final Collection<Renderable> newItems) {
	    
	    this.id = newId;
		this.items = newItems;
	}

	/**
	 * Add an item to the state.
	 * @param item
	 */
	public final void addItem(final Renderable item) {
		this.items.add(item);
	}

	/**
	 * Return all items contained by this page. Items may be plain items, or
	 * groups.
	 */
	@Override
	public final Collection<Renderable> getItems() {

		return this.items;
	}

	/**
	 * Return the unique id for this state.
	 * @return
	 */
	@Override
	public String getId() {

		return this.id;
	}

	/**
	 * Set the id to this state.
	 * @param newId
	 */
	public void setId(String newId) {

		this.id = newId;
	}
}
