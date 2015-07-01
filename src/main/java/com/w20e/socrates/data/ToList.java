package com.w20e.socrates.data;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.apache.commons.collections.IteratorUtils;


public class ToList implements Transformation {

	@SuppressWarnings("rawtypes")
	@Override
	public Object transform(Object obj) throws TransformationException {
		
		if (obj == null) {
			return null;
		} else if (obj instanceof List) {
			return obj;
		} else if (obj instanceof Iterable) {
			return IteratorUtils.toList(((Iterable) obj).iterator());
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
