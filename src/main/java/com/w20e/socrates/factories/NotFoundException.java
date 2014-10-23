package com.w20e.socrates.factories;

public class NotFoundException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * GQuestionnaire doesn;'t exist at given location supported.
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * Given Protocol is not supported.
	 * 
	 * @param message
	 *            some cognitive message.
	 */
	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final String message, final Throwable exception) {
		super(message, exception);
	}

	public NotFoundException(final Throwable exception) {
		super(exception);
	}

}
