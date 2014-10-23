package com.w20e.socrates.rendering;


public class Range extends Input {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Start range at...
	 */
	private int start = 0;
	
	/**
	 * Stop range at...
	 */
	private int end = 0;
	
	/**
	 * Step size.
	 */
	private int step = 1;

	/**
	 * Construct new range.
	 * @param newId
	 */
	public Range(final String newId) {

		super(newId);
		setType("range");
	}

	
	public int getEnd() {
	
		return this.end;
	}

	public void setEnd(int newEnd) {
	
		this.end = newEnd;
	}
	

	public int getStart() {
	
		return this.start;
	}

	public void setStart(int newStart) {
	
		this.start = newStart;
	}
	

	public int getStep() {
	
		return this.step;
	}

	public void setStep(int newStep) {
	
		this.step = newStep;
	}
	


}
