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

import java.io.Serializable;

import com.w20e.socrates.data.Node;

/**
 * Default implementation of a Node as found in the Socrates QE model. The node
 * is serializable to be able to store the node, or sync over the network.
 * 
 * @author D.A.Dokter
 */
public class NodeImpl implements Node, Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -9123216358008630234L;

	/**
	 * The Value of this Node.
	 */
	private Object nodeValue;

	/**
	 * The default value of this Node.
	 */
	private Object defaultValue;

	/**
	 * The name of this Node (pathRef).
	 */
	private String nodeName;

	/**
	 * Node Construction.
	 * 
	 * @param name
	 *            the Node's name (pathRef)
	 * @param value
	 *            a value of the Node
	 */
	public NodeImpl(final String name, final Object value) {

		this.defaultValue = value;
		this.nodeValue = value;
		this.nodeName = name;
	}

	/**
	 * Node Construction.
	 * 
	 * @param name
	 *            the Node's name (pathRef)
	 */
	public NodeImpl(final String name) {

		this.nodeName = name;
	}

	/**
	 * Return the value for this node.
	 * 
	 * @return the value of this node.
	 */
	@Override
	public Object getValue() {

		return this.nodeValue;
	}

	/**
	 * Set the value for this node.
	 * 
	 * @param value
	 *            the value for the node.
	 */
	@Override
	public void setValue(final Object value) {

		this.nodeValue = value;
	}

	/**
	 * Set default value. If the node value is still null, this value will also
	 * be set.
	 * 
	 * @param value
	 *            the default value to use.
	 */
	public void setDefaultValue(final Object value) {

		this.defaultValue = value;
		if (this.nodeValue == null) {
			this.nodeValue = value;
		}
	}

	/**
	 * @see com.w20e.socrates.data.Node#getName()
	 */
	@Override
	public String getName() {
		return this.nodeName;
	}

	/**
	 * Reset the node to it's default value.
	 */
	@Override
	public void reset() {

		this.nodeValue = this.defaultValue;
	}

	/**
	 * toString override.
	 * 
	 * @return Nicely formatted string representation of this Node
	 */
	@Override
	public String toString() {

		return getName();
	}
}
