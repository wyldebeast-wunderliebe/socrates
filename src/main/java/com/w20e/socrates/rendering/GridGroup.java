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
 * @author dokter Implement grouping of items. The Group holds some info on
 *         layout of items.
 */
public final class GridGroup extends GroupImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Columns in case of grid layout.
	 */
	private int cols = 2;

	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public GridGroup(String id) {
		super(id);
	}

	/**
	 * @return Returns the number of columns that should be used in case of a
	 *         grid layout.
	 */
	public int getCols() {

		return this.cols;
	}

	/**
	 * Set the number of columns to use. This is only useful for grid layout.
	 * 
	 * @param newCols
	 *            The number of columns to use for grid layout.
	 */
	public void setCols(final int newCols) {

		this.cols = newCols;
	}
}
