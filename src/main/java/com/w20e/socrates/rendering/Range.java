package com.w20e.socrates.rendering;

import java.util.ArrayList;
import java.util.Collection;

public class Range extends Input implements Vocabulary {

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
	 * 
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

	@Override
	public final Collection<Option> getOptions() {

		ArrayList<Option> options = new ArrayList<Option>();

		for (int i = this.min; i <= this.max; i += this.step) {
			options.add(new Option(String.valueOf(i), String.valueOf(i)));
		}

		return options;
	}

	@Override
	public Collection<Option> getOptions(String refvalue) {

		return this.getOptions();
	}
}
