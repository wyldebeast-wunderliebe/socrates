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

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.w20e.socrates.data.RestrictionViolation;
import com.w20e.socrates.data.TransformationException;
import com.w20e.socrates.data.TypeChecker;
import com.w20e.socrates.data.XSBoolean;

public class Checkbox extends ControlImpl {

    /**
     * UID.
     */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

    /**
     * Construct input type.
     */
    public Checkbox(String id) {
        super(id);
        setType("checkbox");
    }

    @SuppressWarnings("unchecked")
	@Override
	public Object processInput(Map<String, Object> data) {

        Object val = data.get(getId());

        if (val == null || "".equals(val)) {
            return null;
        }
        
        if (val instanceof List) {
            val = ((List<Object>) val).get(0);
        }

        return val;
    }

    /**
     * Return lexical value for boolean.
     */
    @Override
	public Object getDisplayValue(Object value, Class<?> datatype, Locale locale) {

        try {
            return TypeChecker.evaluateLexical(XSBoolean.class, value, locale);
        } catch (TransformationException e) {
            return Boolean.FALSE;
        } catch (RestrictionViolation e) {
            return Boolean.FALSE;
        }
    }
}
