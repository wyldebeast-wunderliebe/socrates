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
 * File      : ItemPropertiesImpl.java
 * Classname : ItemPropertiesImpl
 * Author    : Duco Dokter
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.21 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.model.ItemProperties;

/**
 * Implementation class for item properties.
 */
public class ItemPropertiesImpl implements ItemProperties {

    /**
     * Id for this properties.
     */
    private String id;

    /**
     * Bind expression for this properties.
     */
    private String bind;

    /**
     * Type to use for this properties. Defaults to 'XSString'.
     */
    private Class<?> type = XSString.class;

    /**
     * P3P type for this properties.
     */
    private String p3pType;

    /**
     * Determine whether an Item is readonly. Default is false.
     */
    private Expression readonly = new XBoolean(false);

    /**
     * Determine whether an Item is required. Default is false.
     */
    private Expression required = new XBoolean(false);

    /**
     * Determine whether an Item is relevant. Default is true.
     */
    private Expression relevant = new XBoolean(true);

    /**
     * The constraint for this properties.
     */
    private Expression constraint = new XBoolean(true);

    /**
     * Expression holding calculation of value for this item.
     */
    private Expression calculate;

    /**
     * Creates a new <code>ItemPropertiesImpl</code> instance.
     * 
     * @param propsId
     *            unique id for properties within model.
     * @param pathRef
     *            Path expression for binding the properties to a specific Node.
     */
    public ItemPropertiesImpl(final String propsId) {

        this.id = propsId;
    }

    /**
     * Unique id for this properties object within a model.
     * 
     * @return Properties id
     */
    public final String getId() {

        return this.id;
    }

    /**
     * Return the type for this item.
     * 
     * @return the item type class, like XSString or XSBoolean.
     */
    public final Class<?> getType() {

        return this.type;
    }

    /**
     * Describe <code>setType</code> method here.
     * 
     * @param itemType
     *            a <code>Class</code> value
     */
    public final void setType(final Class<?> itemType) {

        this.type = itemType;
    }

    /**
     * Get the p3p type for this item. This can be used to specify a privacy
     * type for the Item.
     * 
     * @return p3p type
     */
    public final String getP3PType() {

        return this.p3pType;
    }

    /**
     * Set the p3p type for this item. This can be used to specify a privacy
     * type for the Item.
     * 
     * @param t
     *            The type to use for p3p.
     */
    public final void setP3PType(final String t) {

        this.p3pType = t;
    }

    /**
     * Set the expression used to determine whether the item is read-only.
     * 
     * @param expr
     *            an <code>Expression</code> value
     */
    public final void setReadOnly(final Expression expr) {

        this.readonly = expr;
    }

    /**
     * Return the expression used to determine readonly-ness.
     * 
     * @return an <code>Expression</code> value
     */
    public final Expression getReadOnly() {

        return this.readonly;
    }

    /**
     * Set the expression used to determine requiredness.
     * 
     * @param expr
     *            an <code>Expression</code> value
     */
    public final void setRequired(final Expression expr) {

        this.required = expr;
    }

    /**
     * Return the expression used to determine required-ness.
     * 
     * @return an <code>Expression</code> value
     */
    public final Expression getRequired() {

        return this.required;
    }

    /**
     * Set the expression used to determine relevance.
     * 
     * @param expr
     *            an <code>Expression</code> value
     */
    public final void setRelevant(final Expression expr) {

        this.relevant = expr;
    }

    /**
     * Return the expression used to determine relevance.
     * 
     * @return an <code>Expression</code> value
     */
    public final Expression getRelevant() {

        return this.relevant;
    }

    /**
     * Fetch the <code>Constraint</code> of the current object. This might very
     * well be a complex <code>Expression</code>.
     * 
     * @return possibly a complex Expression
     */
    public final Expression getConstraint() {

        return this.constraint;
    }

    /**
     * Describe <code>setConstraint</code> method here.
     * 
     * @param expr
     *            an <code>Expression</code> value
     */
    public final void setConstraint(final Expression expr) {

        this.constraint = expr;
    }

    /**
     * Set the calculation expression for this item.
     * 
     * @param e
     *            expression to use.
     */
    public final void setCalculate(final Expression e) {

        this.calculate = e;
    }

    /**
     * Return the expression used to calculate this item's value.
     * 
     * @return expression.
     */
    public final Expression getCalculate() {

        return this.calculate;
    }

    /**
     * Set the bind expression for this properties.
     * 
     * @param bind
     */
    public void setBind(String newBind) {
        this.bind = newBind;
    }

    /**
     * Get the bind expression for this properties.
     * 
     * @param bind
     */
    public String getBind() {
        return bind;
    }
}
