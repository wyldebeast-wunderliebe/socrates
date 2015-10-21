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

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */


public class ToDoubleFromCurrency implements Transformation {
	
	/**
	 * Return Double value for this object. If the argument is null, null is returned
	 * 
	 * @param obj
	 *            object to convert.
	 * @return double object
	 */
	@Override
	public final Object transform(final Object obj) {
		
		return obj; // do nothing in this transform
	}

	@Override
	public final Object transform(final Object obj, final Locale locale) {
		
		if (locale == null) {
			return transform(obj);
		}

		try {
		
			if (obj instanceof String) {
				// try to parse this as a currency
				
				// first a real dumb trick: if only a comma or a dot is used
				// and it is followed by 1 or 2 digits, then assume it's the
				// decimal seperator, no matter what the locale
				if (obj.toString().matches("^\\d+[.,]{1}\\d{1,2}$")) {
					String decimal_str = obj.toString().replace(",", ".");
					return Double.parseDouble(decimal_str);
				}
				
				Number val = NumberFormat.getCurrencyInstance(locale).parse(obj.toString());
				return Double.valueOf(val.doubleValue());
			}
			
		} catch (Exception e) {
			// whatever
		}
		
		return obj; // do nothing in this transform
	}

}

