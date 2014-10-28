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
 * File      : NotEquals.java
 * Author    : Duco Dokter
 * Created   : Fri Aug 27 16:16:38 2004
 * Version   : $Id: NotEquals.java,v 1.8 2006/11/09 10:30:08 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 *
 */

package com.w20e.socrates.expression;


/**
 * Implementation of 'not equals' operator.
 *
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class NotEquals extends AbstractOperationImpl {

  /**
   * Return boolean representation of evaluating the expression.
   *
   * @return boolean representation of evaluating the expression.
   */
  @Override
public final boolean toBoolean() {

    return !getLeftOperand().eval().equals(getRightOperand().eval());
  }

  /**
   * Return String representation of evaluating the expression.
   *
   * @return String representation of evaluating the expression.
   */
  @Override
public final String toString() {

    return getLeftOperand().toString() + " != " + getRightOperand().toString();
  }

  /**
   * Evaluate the epxression to an XObject.
   *
   * @return the XObject result of evaluation.
   */
  @Override
public final XObject eval() {

    return new XBoolean(toBoolean());
  }
}
