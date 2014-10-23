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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.w20e.socrates.model.InvalidPathExpression;

/**
 * An <code>Instance</code> object holds <code>Node</code> objects, or 'data
 * items'. The Instance is ordered in a hierarchical fashion meaning it may be
 * questioned using path expressions. The Instance object is also ordered
 * sequentially, so nodes retrieved by the 'getNodes' operation are guaranteed
 * to be in the order they were added. Nodes may be retrieved using expressions
 * containing wildcards. The specific path matching algorithm is unspecified,
 * although something like XPath would be a convenient base. The instance should
 * be serializable, to enable all kinds of serialization for example for session
 * replication or just storage. It is up to the implementing classes to actually
 * do something usefull when serializing.
 * 
 * @author D.A.Dokter
 */
public interface Instance extends Serializable {

	/**
	 * Get the instance's id.
	 * 
	 * @return id
	 */
	String getId();

	/**
	 * Use a path expression to question the <code>Instance</code> for a
	 * <code>List</code> of <code>Node</code>s. The nodes are returned in the
	 * order they were added.
	 * 
	 * @param pathExpr
	 *            String expression for identifying nodes.
	 * @return A <code>List</code> of <code>Node</code> objects (at least one),
	 *         <b>null </b> if none found.
	 * @throws InvalidPathExpression
	 *             when pathExpr is incorrect
	 */
	List<Node> getNodes(String pathExpr) throws InvalidPathExpression;

	/**
	 * Return all nodes for this instance.
	 * 
	 * @return all nodes.
	 */
	List<Node> getAllNodes();

	/**
	 * Use a path expression to question the <code>Instance</code> for a
	 * <code>Node</code> object. If the path expression refers to more than one
	 * node, an exception is thrown.
	 * 
	 * @param pathExpr
	 *            String expression for identifying one unique node.
	 * @return A <code>Node</code> object, <b>null </b> if none found.
	 * @throws InvalidPathExpression
	 *             when pathExpr is incorrect
	 */
	Node getNode(String pathExpr) throws InvalidPathExpression;

	/**
	 * Get the meta data for this instance. Meta data can contain just about
	 * anything, but the name part must be a string.
	 * 
	 * @return the meta data.
	 */
	Map<String, Object> getMetaData();

}
