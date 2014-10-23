package com.w20e.socrates.data;

public class XSList extends DataTypeImpl {

	/**
	 * Construct new XSString object.
	 */
	public XSList() {
		super();
		addTransformation(new ToList());
	}
}
