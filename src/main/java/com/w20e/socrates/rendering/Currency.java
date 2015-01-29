package com.w20e.socrates.rendering;

import java.util.Locale;
import java.util.Map;

import com.w20e.socrates.data.TypeChecker;
import com.w20e.socrates.data.XSAmount;

public class Currency extends ControlImpl {

	public Currency(String id) {
		super(id);
		setType("currency");
	}

	/**
	 * Serial UID.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Return display value for this control. This should return the underlying
	 * value formatted for locale.
	 */
	@Override
	public Object getDisplayValue(Object value, Class<?> datatype, Locale locale) {

		try {
            return TypeChecker.evaluateLexical(XSAmount.class, value, locale);
        } catch (Exception e) {
            return "";
        }
	}

	/**
	 * The processing of the amount control should cater for split amount parts. 
	 */
    @Override
    public Object processInput(Map<String, Object> data) {

        String value = "";
        
        if (!data.containsKey(getId()) && !data.containsKey(getId() + "left")) {
        	return null;
        }
        
        if (data.get(getId() + "left") != null) {
            value = data.get(getId() + "left").toString();
        }

        if (data.get(getId() + "right") != null) {
            value += "." + data.get(getId() + "right").toString();
        }

        if (!"".equals(value)) {
            return value;
        }
        
        if (data.get(getId()) == null || "".equals(data.get(getId()))) {
            return null;
        }

        return data.get(getId());

    }

}
