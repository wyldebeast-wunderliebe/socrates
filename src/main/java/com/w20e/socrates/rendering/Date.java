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

package com.w20e.socrates.rendering;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Control for date input. Date format defaults to dd/MM/yyyy. See SimpleDateFormat
 * for possible formats. To set the format, use the setProperty method of the
 * Properties class.
 * @author dokter
 *
 */
public class Date extends ControlImpl {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Formatter for parsing dates.
	 */
	private DateFormat formatter;
	
	/**
	 * Construct input type.
	 */
	public Date(String id) {
		super(id);
		setType("date");
		setProperty("format", "dd/MM/yyyy");
	}

	/**
	 * Process data. This will return the String representation of the data
	 * for this control's id in the map.
	 * @todo We might need a Locale here...
	 */
	@Override
	public Object processInput(Map<String, Object> data) {

	    if (!data.containsKey(getId())) {
	        throw new RuntimeException("No key found for " + getId());
	    }

		try {
	        this.formatter = new SimpleDateFormat(getProperty("format"));
			return this.formatter.parse(data.get(getId()).toString());
		} catch (ParseException e) {
		    return null;
		}
	}

	/**
	 * Return the display value for the given value.
	 * @todo Should we use the locale to format the date? I would think so...
	 */
	@Override
	public Object getDisplayValue(Object value, Class<?> datatype, Locale locale) {

	    try {
	        this.formatter = new SimpleDateFormat(getProperty("format"));
	        return this.formatter.format(value);
	    } catch (Exception e) {
	        return "";
	    }
	}
}
