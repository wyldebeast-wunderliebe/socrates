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
public final class FlowGroup extends GroupImpl {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Orientation in case of flow layout.
	 */
	private String orientation = "vertical";

	public FlowGroup(String id) {
		super(id);
	}

	/**
	 * @return Returns the orientation.
	 */
	public String getOrientation() {

		return this.orientation;
	}

	/**
	 * @param newOrientation
	 *            The orientation to set. This should be vertical or horizontal.
	 */
	public void setOrientation(final String newOrientation) {

		if ("vertical".equals(newOrientation) || "horizontal".equals(newOrientation)) {
			this.orientation = newOrientation;
		}
	}
}
