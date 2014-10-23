package com.w20e.socrates.formatting;

/**
 * Exception for erroneous conditions in formatting. The exception should
 * provide information on the specific error condition for this formatter.
 * 
 * @author dokter
 * 
 */
public class FormatException extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	public FormatException() {
		super();
	}

	public FormatException(final String message) {
		super(message);
	}
}
