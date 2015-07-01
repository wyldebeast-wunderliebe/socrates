package com.w20e.socrates.data;

public class XSList extends DataTypeImpl {

	/**
	 * Construct new XSList object.
	 */
	public XSList() {
		super();
		addTransformation(new ToList());
	}
}
