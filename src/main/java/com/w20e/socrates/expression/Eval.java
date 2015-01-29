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

package com.w20e.socrates.expression;

/**
 * The Eval class is intended to provide some means of evaluating expressions
 * runtime. Note that NOTHING is actually done here, it's just a skeleton
 * implementation.
 * @author dokter
 *
 */
public class Eval extends XString {
	
	/**
	 * uid.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

/**
   * Create new Eval expression.
   * @param expr expression
   */
  public Eval(final String expr) {

    super(expr);
  }
}
