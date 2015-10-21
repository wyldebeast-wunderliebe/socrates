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
 * File      : NodeValidator.java
 * Classname : NodeValidator
 * Author    : Duco Dokter
 * Date      : 14 Jan 2006
 * Version   : $Revision: 1.14 $
 * Copyright : Wyldebeast & Wunderliebe
 */

package com.w20e.socrates.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.data.TypeChecker;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XObject;


/**
 * Implementation class for validation of a node, given an instance and a model.
 * This implementation provides for a complete decoupling between the Node and
 * the ItemProperties, except when checking for constraints, etc. This enables a
 * flyweight pattern implementation of the Model, where all data instances share
 * the same model.
 * 
 * @author D.A.Dokter
 */
public final class NodeValidator {

    /**
     * Initialize this class' logging.
     */
    private static final Logger LOGGER = Logger
            .getLogger(NodeValidator.class.getName());

    /**
     * Privatize constructor.
     */
    private NodeValidator() {
        // Empty constructor.
    }

    /**
     * Get the value of the wrapped node.
     * 
     * @param node
     *            Node to get value of
     * @param props
     *            ItemProperties to use in calculations
     * @param inst
     *            Instance to use.
     * @param model
     *            Model to use
     * @return the value of the wrapped node.
     */
    public static XObject getValue(final Node node, final ItemProperties props,
            final Model model, final Instance inst) {

        /*
         * We need to calculate the value. This could be calling another item
         * value that needs calculation, so let's beware of recursion here,
         * shall we...
         */
        try {
            if (props.getCalculate() != null) {
            	
                    Object value = XRefSolver.resolve(model, inst,
                            props.getCalculate(), node).eval().toObject();
                return TypeChecker.evaluate(props.getDatatype(), value, null);
            }
            
            if (node == null) {
                return Undef.UNDEF;
            }
                        
            if (("".equals(node.getValue()) || node.getValue() == null) && props.getDefault() != null) {
            	
            	Object value = XRefSolver.resolve(model, inst,
            					props.getDefault(), node).eval().toObject();
            	
            	node.setValue(TypeChecker.evaluate(props.getDatatype(),
            			value, null));
			}
            
            return TypeChecker.evaluate(props.getDatatype(), node.getValue(), null);

        } catch (Exception e) {
        	try {
        		LOGGER.log(Level.FINE, "Exception in getting node value for " + node.getName() +"; returning Undef");
        		LOGGER.log(Level.FINE, "Expression: " + XRefSolver.resolve(model, inst,
        				props.getCalculate(), node).toString());
        	} catch (Exception en) {
        		// pass
        	}
            return Undef.UNDEF;
        }
    }

    /**
     * Get the untyped value of the wrapped node.
     * 
     * @param node
     *            Node to get value of
     * @param props
     *            ItemProperties to use in calculations
     * @param inst
     *            Instance to use.
     * @param model
     *            Model to use
     * @return the value of the wrapped node.
     */
    public static Object getRawValue(final Node node,
            final ItemProperties props, final Model model, final Instance inst) {

        /*
         * We need to calculate the value. This could be calling another item
         * value that needs calculation, so let's beware of recursion here,
         * shall we...
         */
        if (props.getCalculate() != null) {

            return XRefSolver.resolve(model, inst, props.getCalculate(), node).eval().toObject();
        }

        if (node == null) {
            return Undef.UNDEF;
        }
        
        if (("".equals(node.getValue()) || node.getValue() == null) && props.getDefault() != null) {
        	
        	Object value = XRefSolver.resolve(model, inst,
        					props.getDefault(), node).eval().toObject();
        	
        	node.setValue(TypeChecker.evaluate(props.getDatatype(), value, null));
		}

        return node.getValue();
    }

    /**
     * Whether or not the current object (for instance, an <code>Item</code>)
     * may be edited.
     * 
     * @param node
     *            Node to validate
     * @param props
     *            Itemproperties containing the validation rules
     * @param model
     *            Model to use
     * @param inst
     *            instance to use.
     * @return possibly a complex Expression
     */
    public static boolean isReadOnly(final ItemProperties props,
            final Instance inst, final Model model) {

        try {
            return XRefSolver.resolve(model, inst, props.getReadOnly(), null).toBoolean();
        } catch (Exception e) {
            LOGGER.warning("Error in resolving expression " + props.getReadOnly().toString());
            return false;
        }
    }

    /**
     * Whether or not the current object (for instance, an <code>Item</code>) is
     * required.
     * 
     * @param node
     *            Node to validate
     * @param props
     *            Itemproperties containing the validation rules
     * @param model
     *            Model to use
     * @param inst
     *            The instance to use.
     * @return possibly a complex Expression
     */
    public static boolean isRequired(final ItemProperties props,
            final Instance inst, final Model model) {

        try {
            return XRefSolver.resolve(model, inst, props.getRequired(), null).toBoolean();
        } catch (Exception e) {
            LOGGER.warning("Error in resolving expression " + props.getRequired().toString());
            return false;
        }
    }

    /**
     * Whether or not the current object is relevant in the complete set of
     * objects (for instance, a set of <code>Item</code>s.
     * 
     * @param node
     *            Node to validate
     * @param props
     *            Itemproperties containing the validation rules
     * @param model
     *            Model to use
     * @param inst
     *            The instance to use.
     * @return whether the item is relevant.
     */
    public static boolean isRelevant(final ItemProperties props,
            final Instance inst, final Model model) {

        try {
            return XRefSolver.resolve(model, inst, props.getRelevant(), null).toBoolean();
        } catch (Exception e) {
            LOGGER.warning("Error in resolving expression " + props.getRelevant().toString());
            return true;
        }
    }

    /**
     * Check all constraints for this item. Validation is performed after the
     * following rules: - if the node is required, and the node's value is not
     * set (null or undef, or the empty string, an error is thrown. - if the
     * node's value is not null nor Undef, do type checking. - if the constraint
     * for a node is not met, throw an error
     * 
     * @param node
     *            Node to validate
     * @param props
     *            Itemproperties containing the validation rules
     * @param model
     *            Model to use
     * @param inst
     *            instance to use.
     * @throws Exception
     *             In whatever case of failure...
     */
    public static void validate(final Node node, final ItemProperties props,
            final Instance inst, final Model model) throws ConstraintViolation {

        Object value = node.getValue();
                
        // Ok, now let's go.
        if ((value == null || "".equals(value.toString()) || value instanceof Undef)
                && isRequired(props, inst, model)) {

            throw new ConstraintViolation(ConstraintViolation.REQUIRED, node
                    .getName());
        }

        // Type validation is only done if there's an actual value
        if (value != null && !(value instanceof Undef)) {
            try {
                TypeChecker.validate(props.getDatatype(), value);
            } catch (Exception e) {
                throw new ConstraintViolation(ConstraintViolation.TYPE, node
                        .getName());
            }
        }
        
        if (XRefSolver.resolve(model, inst, props.getConstraint(), node).toBoolean()) {
            return;
        }
        throw new ConstraintViolation(ConstraintViolation.FALSE, node.getName());
    }

}
