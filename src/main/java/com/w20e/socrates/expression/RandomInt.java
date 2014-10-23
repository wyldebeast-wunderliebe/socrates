package com.w20e.socrates.expression;

import java.util.Random;

/**
 * Implement random function that uses a range, and returns an int. Returns a
 * pseudorandom, uniformly distributed int value between the lower bound
 * (inclusive) and the upper bound (exclusive). If only one operand is provided,
 * this will be used as upper bound, and a lower bound of 0 is used. If no
 * operands are provided, a random positive integer will be returned.
 * 
 * @author dokter
 * 
 */
public class RandomInt extends AbstractFunctionImpl {

	private final Random randomizer = new Random();

	/**
	 * String representation of random.
	 */
	public String toString() {

		return "random(" + this.getOperandsString() + ")";
	}

	/**
	 * Return random number in given range.
	 */
	public XObject eval() {

		int start = 0, stop = 0;

		if (this.getOperands().length > 1) {
			start = this.getOperands()[0].eval().toNumber().intValue();
			stop = this.getOperands()[1].eval().toNumber().intValue();
		} else if (this.getOperands().length == 0) {
			return new XNumber(this.randomizer.nextInt());
		} else {
			stop = this.getOperands()[0].eval().toNumber().intValue();
		}

		if ((stop - start) <= 0) {
			return Undef.UNDEF;
		}

		return new XNumber(this.randomizer.nextInt(stop - start) + start);
	}

	/**
	 * Return false if eval's to false.
	 */
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
