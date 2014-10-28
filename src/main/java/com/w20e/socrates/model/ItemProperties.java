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
 * File      : ItemProperties.java
 * Classname : ItemProperties
 * Author    : Duco Dokter
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.19 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.util.List;

import com.w20e.socrates.expression.Expression;

/**
 * Item properties definition. This specifies the possible Item attributes,
 * according to the XForms specs.
 * 
 * @author D.A.Dokter
 */
public interface ItemProperties {

	/**
	 * Get the id for this properties. This is needed by controls, to be able to
	 * bind to a properties object.
	 * 
	 * @return id of the item properties
	 */
	String getId();

	/**
     * Get the bind expression for this properties object. This expression is used to bind
     * properties to a (set of) node(s).
     * 
     * @return id of the item properties
     */
    List<String> getBind();

	/**
	 * Return the type for this item. This is an implementation of the XForms
	 * data types.
	 * 
	 * @return the item type class, like XSString or XSBoolean.
	 */
	Class<?> getType();

	/**
	 * Describe <code>setType</code> method here.
	 * 
	 * @param type
	 *            a <code>Class</code> value
	 */
	void setType(Class<?> type);

	/**
	 * Get the p3p type for this item. This can be used to specify a privacy
	 * type for the Item.
	 * 
	 * @return string representation of the P3P type.
	 */
	String getP3PType();

	/**
	 * Set the p3p type for this item. This can be used to specify a privacy
	 * type for the Item.
	 * 
	 * @param type
	 *            the P3P type
	 */
	void setP3PType(String type);

	/**
	 * Fetch the <code>Constraint</code> of the current object. This might very
	 * well be a complex <code>Expression</code>.
	 * 
	 * @return possibly a complex Expression
	 */
	Expression getConstraint();

	/**
	 * Describe <code>setConstraint</code> method here.
	 * 
	 * @param e
	 *            an <code>Expression</code> value
	 */
	void setConstraint(Expression constraint);

	/**
	 * Set the expression used to determine whether the item is read-only.
	 * 
	 * @param e
	 *            an <code>Expression</code> value
	 */
	void setReadOnly(Expression expression);

	/**
	 * Get the expression used to determine read-only-ness.
	 * 
	 * @return an <code>Expression</code> value
	 */
	Expression getReadOnly();

	/**
	 * Set the expression used to determine requiredness.
	 * 
	 * @param e
	 *            an <code>Expression</code> value
	 */
	void setRequired(Expression expression);

	/**
	 * Get the expression used to determine requiredness.
	 * 
	 * @return an <code>Expression</code> value
	 */
	Expression getRequired();

	/**
	 * Set the expression used to determine whether the item is relevant.
	 * 
	 * @param e
	 *            an <code>Expression</code> value
	 */
	void setRelevant(Expression expression);

	/**
	 * Get the expression used to determine relevance.
	 * 
	 * @return an <code>Expression</code> value
	 */
	Expression getRelevant();

	/**
	 * Set calculate expression. It would be unadvisable to use this on a non
	 * readonly field.
	 * 
	 * @param e
	 *            expression to use in calculation.
	 */
	void setCalculate(Expression expression);

	/**
	 * Get the epxression used to calculate the value for this field.
	 * 
	 * @return the epxression.
	 */
	Expression getCalculate();
}
