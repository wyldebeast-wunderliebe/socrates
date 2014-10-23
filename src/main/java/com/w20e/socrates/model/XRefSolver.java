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
 * File      : XRefSolver.java
 * Classname : XRefSolver
 * Author    : Duco Dokter
 * Date      : 14 Jan 2005
 * Version   : $Revision: 1.19 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.Function;
import com.w20e.socrates.expression.AbstractFunctionImpl;
import com.w20e.socrates.expression.Operation;
import com.w20e.socrates.expression.TernaryOperation;
import com.w20e.socrates.expression.UnaryOperation;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XRef;
import com.w20e.socrates.expression.XVar;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.data.TypeChecker;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.Model;

/**
 * This class replaces string based refs from an Expression to real refs to a
 * node's value.
 */
public final class XRefSolver {

	/**
	 * privatize constructor.
	 */
	private XRefSolver() {
		// Empty constructor.
	}

	/**
	 * Resolve XRef's based on a string to the actual Node in the instance. This
	 * method recursively searches the expression provided for instances of
	 * com.w20e.expression.XRef, and replaces these for real references to
	 * nodes. If it so happens that the expression holds a ref to self, the actual value on
	 * the node is returned.
	 * 
	 * @param model
	 *            Model to resolve with.
	 * @param inst
	 *            A reference to the Instance object
	 * @param expr
	 *            The Expression to resolve
	 * @param self The originating node. If not null, the recursion will check to make sure it 
	 * doesn't meet itself...
	 * @return the newly created expression, with the local XRef implementation
	 *         in place.
	 * @throws Exception
	 *             when the XRef in the expression has an invalid path, or any
	 *             other nastiness.
	 */
	public static Expression resolve(final Model model, final Instance inst,
			final Expression expr, final Node self) {

	    if (expr == null) {
	        return Undef.UNDEF;
	    }
	    
		if (expr instanceof XVar) {

			try {
				Node node = inst.getNode(expr.toString());

				if (node == null) {
					return Undef.UNDEF;
				}

				ItemProperties props = model.getItemProperties(node.getName());

				if (props == null) {
					props = new ItemPropertiesImpl("p0");
				}

				if (node == self) {
					return TypeChecker.evaluate(props.getType(), node.getValue());
				}

				return new XRef(node, model, inst, props);

			} catch (InvalidPathExpression ipe) {
				return new Undef();
			}
		} else if (expr instanceof com.w20e.socrates.expression.Eval) {
			return new Eval(expr.toString(), model, inst);
		} else {

			if (expr instanceof Operation) {

				Expression newExpr;
                try {
                    newExpr = (Expression) Class.forName(
                    		expr.getClass().getName()).newInstance();
                } catch (InstantiationException e) {
                    return Undef.UNDEF;
                } catch (IllegalAccessException e) {
                    return Undef.UNDEF;
                } catch (ClassNotFoundException e) {
                    return Undef.UNDEF;
                }

				((Operation) newExpr).setLeftOperand(resolve(model, inst,
						((Operation) expr).getLeftOperand(), self));
				((Operation) newExpr).setRightOperand(resolve(model, inst,
						((Operation) expr).getRightOperand(), self));

				return newExpr;
			} else if (expr instanceof UnaryOperation) {

                Expression newExpr;
                try {
                    newExpr = (Expression) Class.forName(
                            expr.getClass().getName()).newInstance();
                } catch (InstantiationException e) {
                    return Undef.UNDEF;
                } catch (IllegalAccessException e) {
                    return Undef.UNDEF;
                } catch (ClassNotFoundException e) {
                    return Undef.UNDEF;
                }

				((UnaryOperation) newExpr).setLeftOperand(resolve(model, inst,
						((UnaryOperation) expr).getLeftOperand(), self));

				return newExpr;
			} else if (expr instanceof TernaryOperation) {

                Expression newExpr;
                try {
                    newExpr = (Expression) Class.forName(
                            expr.getClass().getName()).newInstance();
                } catch (InstantiationException e) {
                    return Undef.UNDEF;
                } catch (IllegalAccessException e) {
                    return Undef.UNDEF;
                } catch (ClassNotFoundException e) {
                    return Undef.UNDEF;
                }

				((TernaryOperation) newExpr).setLeftOperand(resolve(model,
						inst, ((TernaryOperation) expr).getLeftOperand(), self));
				((TernaryOperation) newExpr).setMiddleOperand(resolve(model,
						inst, ((TernaryOperation) expr).getMiddleOperand(), self));
				((TernaryOperation) newExpr).setRightOperand(resolve(model,
						inst, ((TernaryOperation) expr).getRightOperand(), self));

				return newExpr;
			} else if (expr instanceof Function) {

			    AbstractFunctionImpl func;
			    
			    try {
			        func = (AbstractFunctionImpl) Class.forName(expr.getClass().getName()).newInstance();
			    } catch (InstantiationException e) {
			        e.printStackTrace();
                    return Undef.UNDEF;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return Undef.UNDEF;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return Undef.UNDEF;
                }

				Expression[] ops = ((Function) expr).getOperands();
				Expression[] newArgs = new Expression[ops.length];

				for (int i = 0; i < ops.length; i++) {
					newArgs[i] = resolve(model, inst, ops[i], self);
				}
				
				func.setOperands(newArgs);

				return func;
			}

			return expr;
		}
	}
}
