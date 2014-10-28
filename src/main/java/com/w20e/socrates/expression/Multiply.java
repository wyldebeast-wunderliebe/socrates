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
 * File      : Multiply.java
 * Classname : Multiply
 * Author    : Duco Dokter
 * Date      : 18 Jan 2005
 * Version   : $Id: Multiply.java,v 1.8 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation of the multiply operation.
 *
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class Multiply extends AbstractOperationImpl {

  /**
   * Return boolean evaluation of this expression.
   *
   * @return boolean evaluation of this expression. This is true iff
   * the result evaluates to a non-zero value.
   */
  @Override
public final boolean toBoolean() {

    return eval().toBoolean();
  }

  /**
   * Return the string representation of the expression. This is the
   * string representation of the left operand, ' * ' and the string
   * representation of the right operand.
   *
   * @return the string representation of this expression.
   */
  @Override
public final String toString() {

    return getLeftOperand().toString() + " * " + getRightOperand().toString();
  }

  /**
   * Evaluate the operation.
   * @return the evaluated operation as XObject.
   */
  @Override
public final XObject eval() {

    return new XNumber(
        new Double(getLeftOperand().eval().toNumber().doubleValue()
        * getRightOperand().eval().toNumber().doubleValue()));
  }
}
