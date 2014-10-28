/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : InstanceImpl.java
 * Classname : InstanceImpl
 * Author    : Wietze Helmantel
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.35 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.util.PathMatcher;
import com.w20e.socrates.model.util.SimplePathMatcher;

/**
 * Default implementation of an Instance as found in the Socrates QE model. The
 * implementation uses the Node's <code>getName</code> method for matching
 * against a path expression, using the <code>
 * SimplePathMatcher</code> class. When the
 * matching expression doesn't contain XPath wilcard characters, the Node is
 * fetched from a HashTable, to enhance performance.
 */
public class InstanceImpl implements Instance {

    /**
     * UID.
     */
    private static final long serialVersionUID = -3846935990415015789L;

    /**
     * A List of Nodes residing in the Instance.
     */
    private List<Node> nodes;

    /**
     * Map holding Nodes to enhance performance.
     */
    private Map<String, ArrayList<Node>> nodeMap;

    /**
     * Meta data.
     */
    private Map<String, Object> metaData = new HashMap<String, Object>();

    /**
     * Simple matching algorithm.
     */
    private transient PathMatcher matcher = SimplePathMatcher.getInstance();

    /**
     * Id.
     */
    private String id;

    /**
     * Creates a new <code>InstanceImpl</code> instance with an empty. nodes
     * list.
     */
    public InstanceImpl() {
        this.nodes = new ArrayList<Node>();
        this.nodeMap = new HashMap<String, ArrayList<Node>>();
        this.metaData.put("created", new Date());
    }

    /**
     * Creates a new <code>InstanceImpl</code> instance using the List as
     * initial list of Node objects. If the list is null, it is unused, and an
     * empty list is used instead.
     * 
     * @param n
     *            a <code>List</code> value
     */
    public InstanceImpl(final List<Node> n) {
        this.nodeMap = new HashMap<String, ArrayList<Node>>();

        if (n != null) {
            this.nodes = n;
            for (Node node : this.nodes) {
                addToMap(node);
            }
        } else {
            this.nodes = new ArrayList<Node>();
        }
        this.metaData.put("created", new Date());
    }

    /**
     * Set the instance's id.
     * 
     * @param instanceId
     *            Instance's id.
     */
    public final void setId(final String instanceId) {

        this.id = instanceId;
    }

    /**
     * Return the instance's id.
     * 
     * @return id of the instance
     */
    @Override
	public final String getId() {

        return this.id;
    }

    /**
     * Retrieve a list of nodes matching the path expression. The nodes will be
     * in the order they were added.
     * 
     * @param pathExpr
     *            the expression to use for matching
     * @return List of nodes matching the path expression
     * @throws InvalidPathExpression
     *             when the matching algorithm used cannot parse this
     *             expression.
     */
    @Override
	public final List<Node> getNodes(final String pathExpr)
            throws InvalidPathExpression {

        if (!this.matcher.containsWildcard(pathExpr)) {
            if (this.nodeMap.containsKey(pathExpr)) {
                return this.nodeMap.get(pathExpr);
            }
        }

        // Optimization for all nodes expression.
        if ("/*".equals(pathExpr) || "*".equals(pathExpr)) {
            return this.nodes;
        }

        return this.matcher.match(pathExpr, this.nodes);
    }

    /**
     * Return all nodes.
     * 
     * @return a list of all nodes
     */
    @Override
	public final List<Node> getAllNodes() {

        return this.nodes;
    }

    /**
     * Get the node specified by the path epxression. If the expression matches
     * more than one node, only the first is returned. Use the
     * <code>getNodes</code> method if you wish to retrieve all nodes for a given
     * expression.
     * 
     * @throws InvalidPathExpression
     *             when the matching algorithm used cannot parse this expression
     * @param pathExpr
     *            a tree like path query.
     * @return the Node requested or null if not found.
     */
    @Override
	public final Node getNode(final String pathExpr)
            throws InvalidPathExpression {

        if (!this.matcher.containsWildcard(pathExpr)) {
            if (this.nodeMap.containsKey(pathExpr)) {
                return this.nodeMap.get(pathExpr).get(0);
            }
        }
        return this.matcher.matchFirst(pathExpr, this.nodes);
    }

    /**
     * Add the node given as argument, if it is not null.
     * 
     * @param n
     *            the node to add
     */
    public final void addNode(final Node n) {

        if (n != null) {
            this.nodes.add(n);
            addToMap(n);
        }
    }

    /**
     * Get the meta data for this instance.
     * 
     * @return meta data map.
     */
    @Override
	public final Map<String, Object> getMetaData() {

        return this.metaData;
    }

    /**
     * Add nodes to the internal map. If there's already an entry for the node
     * name, add the node to the list, otherwise add a new list.
     * 
     * @param node
     *            the node to add.
     */
    private void addToMap(final Node node) {
        if (!this.nodeMap.containsKey(node.getName())) {
            this.nodeMap.put(node.getName(), new ArrayList<Node>());
        }
        this.nodeMap.get(node.getName()).add(node);
    }

    /**
     * Serialization override. This makes sure to recreate the mathing algorithm
     * used.
     * 
     * @param stream
     *            object stream.
     * @throws IOException
     *             whenever an io error occurs.
     * @throws ClassNotFoundException
     *             when tho object read from the stream is not an Instance.
     */
    private void readObject(final ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        stream.defaultReadObject();
        this.matcher = SimplePathMatcher.getInstance();
    }
}
