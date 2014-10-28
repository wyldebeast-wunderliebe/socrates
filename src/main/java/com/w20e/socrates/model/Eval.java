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

import org.apache.commons.jxpath.ri.Parser;

import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.AbstractExpressionImpl;
import com.w20e.socrates.expression.UnaryOperation;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XObject;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.Model;

/**
 * Implementation class for Eval. This class will resolve the expression
 * of the given Item if possible.
 *
 * @author dokter
 *
 */
public class Eval extends AbstractExpressionImpl
implements UnaryOperation {

  /**
   * Model used during resolving.
   */
  private Model model;

  /**
   * Instance holder.
   */
  private Instance instance;

  /**
   * Left operand.
   */
  private Expression lv;

  /**
   * Create a new Eval class.
   * @param expr Expression to use for eval.
   * @param m Model
   * @param i Instance
   */
  public Eval(final String expr, final Model m, final Instance i) {

    try {
      this.lv = (Expression) Parser.parseExpression(expr,
          ExpressionCompiler.getInstance());
      this.lv =  XRefSolver.resolve(m, i, this.lv, null);
    } catch (Exception e) {
      this.lv = new Undef();
    }

    this.model = m;
    this.instance = i;
  }

  /**
   * Set left operand for this Eval.
   * @param l Left operand.
   */
  @Override
public final void setLeftOperand(final Expression l) {
    this.lv = l;
  }

  /**
   * Get the left operand. This will first resolve the expression
   * that was used in the constructor, and than parse it, and return the
   * eval'ed result of that.
   * @return Left Operand for this expression. This will first parse
   * the eval'ed result of the left operand as string.
   */
  @Override
public final Expression getLeftOperand() {

    try {
      return (Expression) Parser.parseExpression(
          this.lv.eval().toString(), ExpressionCompiler.getInstance());

    } catch (Exception e) {
      return new Undef();
    }
  }

  /**
   * Return string representation of the Eval.
   * @return Left operand unparsed, as string.
   */
  @Override
public final String toString() {

    return this.lv.toString();
  }

  /**
   * Return Boolean representation for this Eval.
   * @return boolean representation of this expression.
   */
  @Override
public final boolean toBoolean() {

     return eval().toBoolean();
  }

  /**
   * Evaluate the left operand. This will call resolving first, and then
   * @return the eval'ed result.
   */
  @Override
public final XObject eval() {

    try {
      return XRefSolver.resolve(this.model, this.instance,
          getLeftOperand(), null).eval();
    } catch (Exception e) {
      return new Undef();
    }
  }
}
