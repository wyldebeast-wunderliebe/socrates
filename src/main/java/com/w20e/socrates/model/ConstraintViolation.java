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

package com.w20e.socrates.model;

/**
 * Thrown when setting the value on an item violates the item's constraint. The
 * violation should always contain a reference to the node it was thrown on.
 * 
 * @author W.G.Helmantel
 */
public final class ConstraintViolation extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -416483165398670315L;

	/**
	 * Node name for this exception.
	 */
	private final String node;

	/**
	 * Required message.
	 */
	public static final String REQUIRED = "Item is required";

	/**
	 * Type violation message.
	 */
	public static final String TYPE = "Type violation";

	/**
	 * Condition evaluates to false.
	 */
	public static final String FALSE = "Constraint evaluates to false";

	/**
	 * Node is readonly.
	 */
	public static final String READONLY = "Read-only";

	/**
	 * Node is non-existing.
	 */
	public static final String NO_SUCH_NODE = "No such node";

	/**
	 * Unknown error.
	 */
	public static final String UNKNOWN = "Unknown";

	/**
	 * Creates a new <code>ConstraintViolation</code> instance.
	 * 
	 * @param msg
	 *            Message for this exception.
	 * @param nodeName
	 *            node to attach this message to.
	 */
	public ConstraintViolation(final String msg, final String nodeName) {

		super(msg);
		this.node = nodeName;
	}

	/**
	 * Return the node this error is thrown for.
	 * 
	 * @return the name of the node.
	 */
	public String getNodeName() {

		return this.node;
	}
}
