package com.w20e.socrates.workflow;

/**
 * Main exception class for process exceptions.
 * 
 * @author dokter
 * 
 */
public class ProcessException extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	public ProcessException() {
		super();
	}

	public ProcessException(final String msg) {
		super(msg);
	}

}
