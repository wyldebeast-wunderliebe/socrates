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
 * File      : XRef.java
 * Classname : XRef
 * Author    : Duco Dokter
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.33 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.data.TypeChecker;
import com.w20e.socrates.data.XSBoolean;
import com.w20e.socrates.data.XSDouble;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.NodeValidator;

/**
 * The XRef holds the following logic: if the underlying value of the node
 * cannot be converted to the type, Undef is returned.
 * 
 * @todo Shouldn't we remote the ItemProperties from the constructor and use the
 *       underlying node's props?
 */
public class XRef extends AbstractExpressionImpl implements XObject {

	/**
	 * uid.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Hold ref to instance.
	 */
	private final Node node;

	/**
	 * Hold model ref.
	 */
	private final Model model;

	/**
	 * Instance holder.
	 */
	private final Instance instance;

	/**
	 * Props for this ref.
	 */
	private final ItemProperties props;

	/**
	 * Creates a new <code>XRef</code> instance.
	 * 
	 * @param n
	 *            an <code>Item</code> reference.
	 * @param i
	 *            Instance
	 * @param m
	 *            Model
	 * @param p
	 *            Itemproperties
	 */
	public XRef(final Node newNode, final Model newModel,
			final Instance newInstance, final ItemProperties newProps) {

		super();
		this.model = newModel;
		this.instance = newInstance;
		this.node = newNode;
		this.props = newProps;
	}

	/**
	 * Return numeric representation of the node's value.
	 * 
	 * @return a <code>Number</code> value.
	 * @TODO The to number stuff should actually first get the underlying value,
	 *       and then convert it to a number.
	 */
	@Override
	public final Number toNumber() {

		return TypeChecker.evaluate(
				XSDouble.class,
				NodeValidator.getRawValue(this.node, this.props, this.model,
						this.instance), null).toNumber();
	}

	/**
	 * Get the object wrapped by this XRef.
	 * 
	 * @return the wrapped value object
	 */
	private final XObject getValue() {

		return NodeValidator.getValue(this.node, this.props, this.model,
				this.instance);
	}

	/**
	 * The signature for this method is that it should return 0 if the
	 * expressions are equal, a value less than zero if the expression provided
	 * as an argument is greater than the expression, and a value bigger than
	 * zero if the expression provided as argument is less than the expression.
	 * If the argument is not an instance of XNumber, a ClassCastException is
	 * thrown.
	 * 
	 * @param expr
	 *            The Expression to compare with
	 * @return the integer value indicating comparison result.
	 */
	@Override
	public final int compareTo(final Expression expr) {

		// Just call underlying object...
		return this.getValue().compareTo(expr);
	}

	/**
	 * Compares this variable to the specified object. The result is true if and
	 * only if the argument is not null and the Expression evaluates to the same
	 * toString on the value of this object.
	 * 
	 * @param obj
	 *            The Object to evaluate.
	 * @return the boolean representation of this object.
	 */
	@Override
	public final boolean equals(final Object obj) {

		if (!(obj instanceof Expression)) {
			return false;
		}

		return this.compareTo((Expression) obj) == 0;
	}

	/**
	 * Return the boolean representation of this XRef. This is true iff the
	 * wrapped Number is not null, and is not a representation of zero.
	 * 
	 * @return the <code>boolean</code> representation of this XNumber.
	 */
	@Override
	public final boolean toBoolean() {

		return TypeChecker.evaluate(
				XSBoolean.class,
				NodeValidator.getRawValue(this.node, this.props, this.model,
						this.instance), null).toBoolean();
	}

	/**
	 * Return the string representation of this XRef.
	 * 
	 * @return the <code>String</code> representation of this XRef.
	 */
	@Override
	public final String toString() {

		return TypeChecker.evaluate(
				XSString.class,
				NodeValidator.getRawValue(this.node, this.props, this.model,
						this.instance), null).toString();
	}

	/**
	 * Return underlying object.
	 * 
	 * @return Node value, converted with class.
	 */
	@Override
	public final Object toObject() {

		return getValue().toObject();
	}

	@Override
	public XObject eval() {

		return getValue();
	}

	/**
	 * Override Object hashCode.
	 */
	@Override
	public final int hashCode() {

		return this.toObject().hashCode();
	}
}
