package com.w20e.socrates.data;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ToList implements Transformation {

	@Override
	public Object transform(Object obj) throws TransformationException {

		if (obj == null) {
			return null;
		} else if (obj instanceof List) {
			return obj;
		} else {
			Object[] objs = { obj };
			
			return Arrays.asList(objs);
		}
	}

	@Override
	public Object transform(Object obj, Locale locale)
			throws TransformationException {

		return this.transform(obj);
	}

}
