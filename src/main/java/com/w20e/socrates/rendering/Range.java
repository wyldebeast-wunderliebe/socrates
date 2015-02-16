package com.w20e.socrates.rendering;


public class Range extends Input {

	/**
	 * UID.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Start range at...
	 */
	private int max = 0;
	
	/**
	 * Stop range at...
	 */
	private int min = 0;
	
	/**
	 * Step size.
	 */
	private int step = 1;

	/**
	 * Construct input type.
	 */
	public Range() {

		super();
	}

	/**
	 * Construct new range.
	 * @param newId
	 */
	public Range(final String newId) {

		super(newId);
		setType("range");
	}

	
	public int getMin() {
	
		return this.min;
	}

	public void setMin(int newMin) {
	
		this.min = newMin;
	}
	

	public int getMax() {
	
		return this.max;
	}

	public void setMax(int newMax) {
	
		this.max = newMax;
	}
	

	public int getStep() {
	
		return this.step;
	}

	public void setStep(int newStep) {
	
		this.step = newStep;
	}
	


}
