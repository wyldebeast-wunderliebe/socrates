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

package com.w20e.socrates.util;

import java.util.List;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.Function;
import com.w20e.socrates.expression.Operation;
import com.w20e.socrates.expression.TernaryOperation;
import com.w20e.socrates.expression.UnaryOperation;
import com.w20e.socrates.expression.XVar;
import com.w20e.socrates.model.Model;

/**
 * This class replaces string based refs from an Expression to real refs to a
 * node's value.
 */
public final class XRefCollector {

	/**
	 * privatize constructor.
	 */
	private XRefCollector() {
		// Empty constructor.
	}

	/**
	 * Collect nodes in this expression. This
	 * method recursively searches the expression provided for instances of
	 * com.w20e.expression.XVar, and replaces these for real references to
	 * nodes.
	 */
	public static void resolve(final Model model, final Instance inst,
			final Expression expr, List<Node> nodes) {

		if (expr instanceof XVar) {

			try {
				Node node = inst.getNode(expr.toString());

				if (node != null && !nodes.contains(node)) {
				    nodes.add(node);
				}

			} catch (Exception e) {
			    // Hmm.
			}
		//} else if (expr instanceof com.w20e.socrates.expression.Eval) {
		    //resolve(model, inst, ((Eval) expr).toString() ,nodes);
		} else {

			if (expr instanceof Operation) {

				resolve(model, inst, ((Operation) expr).getLeftOperand(), nodes);
				resolve(model, inst, ((Operation) expr).getRightOperand(), nodes);

			} else if (expr instanceof UnaryOperation) {

				resolve(model, inst, ((UnaryOperation) expr).getLeftOperand(), nodes);

			} else if (expr instanceof TernaryOperation) {

				resolve(model, inst, ((TernaryOperation) expr).getLeftOperand(), nodes);
				resolve(model, inst, ((TernaryOperation) expr).getMiddleOperand(), nodes);
				resolve(model, inst, ((TernaryOperation) expr).getRightOperand(), nodes);
			} else if (expr instanceof Function) {

				Expression[] ops = ((Function) expr).getOperands();

				for (int i = 0; i < ops.length; i++) {
					resolve(model, inst, ops[i], nodes);
				}
			}
		}
	}


	/**
	 * Collect references to non-existent nodes in this expression. This
	 * method recursively searches the expression provided for instances of
	 * com.w20e.expression.XVar.
	 */
	public static void findXRefErrors(final Instance inst,
			final Expression expr, List<String> errors) {

		if (expr instanceof XVar) {

			try {
				if (inst.getNode(expr.toString()) == null && !errors.contains(expr.toString())) {
				    errors.add(expr.toString());
				}
			} catch (Exception e) {
				if (!errors.contains(expr.toString())) {
				    errors.add(expr.toString());
				}
			}
		} else if (expr instanceof com.w20e.socrates.expression.Eval) {
		    //findXRefErrors(model, inst, ((Eval) expr).toString() ,nodes);
		} else {

			if (expr instanceof Operation) {

				findXRefErrors(inst, ((Operation) expr).getLeftOperand(), errors);
				findXRefErrors(inst, ((Operation) expr).getRightOperand(), errors);

			} else if (expr instanceof UnaryOperation) {

				findXRefErrors(inst, ((UnaryOperation) expr).getLeftOperand(), errors);

			} else if (expr instanceof TernaryOperation) {

				findXRefErrors(inst, ((TernaryOperation) expr).getLeftOperand(), errors);
				findXRefErrors(inst, ((TernaryOperation) expr).getMiddleOperand(), errors);
				findXRefErrors(inst, ((TernaryOperation) expr).getRightOperand(), errors);
			} else if (expr instanceof Function) {

				Expression[] ops = ((Function) expr).getOperands();

				for (int i = 0; i < ops.length; i++) {
					findXRefErrors(inst, ops[i], errors);
				}
			}
		}
	}

}
