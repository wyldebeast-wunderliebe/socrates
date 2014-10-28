package com.w20e.socrates.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


public class ToDate implements Transformation {

	@Override
	public Object transform(final Object obj) 
	throws TransformationException {

		if (obj == null || "".equals(obj.toString())) {
			throw new TransformationException("Couldn't transform " + obj + " to Date");
		}
		
		// Well, we'll have to make some assumptions here...
		
		if (obj instanceof Date) {
			return obj;
		}
		
		try {
		    final DateFormat parser = DateFormat.getDateInstance(DateFormat.SHORT);
			return parser.parse(obj.toString());
		} catch (ParseException e) {
            throw new TransformationException("Couldn't transform " + obj + " to Date");
		}
	}


	   @Override
	public Object transform(final Object obj, final Locale locale) {

	        if (obj == null || "".equals(obj.toString())) {
	            return null;
	        }
	        
	        // Well, we'll have to make some assumptions here...
	        
	        if (obj instanceof Date) {
	            return obj;
	        }
	        
	        try {
	            return DateFormat.getDateInstance(DateFormat.SHORT, locale).parse(obj.toString());
	        } catch (ParseException e) {
	            // tough...
	        }

	        return null;
	    }

}
