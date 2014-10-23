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
 * File      : Round.java
 * Classname : Round
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.3 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;


/**
 * Implementation of the 'round' operation. TODO: this should really be a
 * function implementation.
 */
public class Round extends AbstractFunctionImpl {

    /**
     * Return the boolean result of evaluating this expression.
     * 
     * @return true if the operation is evaluated to a non-zero value, else
     *         return false.
     */
    public final boolean toBoolean() {

        return eval().toBoolean();
    }

    /**
     * Return the String representation of this operation.
     * 
     * @return the string representation of this operation.
     */
    public final String toString() {

        return "round(" + getOperandsString() + ")";
    }

    /**
     * Evaluate the operation, by trying to cast left and right operant to an
     * XNumber, and subtracting the right operand from the left.
     * 
     * @return the XObject that is the result of this evaluation. In case of
     *         this operation this is always an XNumber.
     */
    public final XObject eval() {

        final Expression[] ops = getOperands();
        
        // rounding defaults to 0
        int decimals = 0;
        
        if (ops.length > 1) {
            decimals = ops[1].eval().toNumber().intValue();
        }

        if (decimals > 0) {
            return new XNumber(Double.valueOf(round(ops[0].eval().toNumber().doubleValue(), decimals)));           
        } else {
            return new XNumber(Math.round(ops[0].eval().toNumber().doubleValue()));
        }
    }
            
    /**
     * Round with given precision.
     * @param val value
     * @param decimals decimal places
     * @return
     */
    private double round(final double val, final int decimals) {

    	final double power = Math.pow(10, decimals);
    	
        return Math.round(val * power) / power;
    }
}
