package com.w20e.socrates.factories;

public class InvalidException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 1L;

	public InvalidException() {
		super();
	}

	public InvalidException(final String message) {
		super(message);
	}

	public InvalidException(final Throwable exception) {
		super(exception);
	}

	public InvalidException(final String message, final Throwable exception) {
		super(message, exception);
	}

}
