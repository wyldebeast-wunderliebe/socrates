package com.w20e.socrates.data;

public class XSDate extends DataTypeImpl {

	/**
	 * Construct new XSDate.
	 */
	public XSDate() {
		super();
		addRestriction(new IsDate());
		addTransformation(new ToDate());
	}

}
