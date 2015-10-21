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

package com.w20e.socrates.data;

/**
 * Data type for amount.
 * 
 * @author dokter
 */
public class XSAmount extends XSFloat {

	/**
	 * Create new Amount object.
	 */
	public XSAmount() {
		super();		
		addTransformation(new ToDoubleFromCurrency(), true); // prepend!
		addLexicalTransformation(new NullToZeroAsFloat());
		addLexicalTransformation(new CurrencyFormat());
	}
}
