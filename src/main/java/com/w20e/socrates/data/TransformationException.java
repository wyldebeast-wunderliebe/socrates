package com.w20e.socrates.data;

/**
 * Exception that is thrown when a given object can't be transformed to the
 * expected datatype.
 * @author dokter
 *
 */
public class TransformationException extends Exception {

	/**
	 * Create new exception with given message.
	 * @param message
	 */
	public TransformationException(final String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
