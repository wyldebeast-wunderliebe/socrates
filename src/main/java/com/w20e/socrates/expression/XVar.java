package com.w20e.socrates.expression;

/**
 * Placeholder for references in an expression. This should be resolved to the
 * real value at runtime.
 * @author dokter
 *
 */
public class XVar extends XString {

	/**
	 * uid.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Construct the XVar with the given node id.
	 * @param ref
	 */
	public XVar(final String ref) {
		super(ref);
	}

}
