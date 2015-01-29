package com.w20e.socrates.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
public class Sample extends AbstractFunctionImpl {

	/**
	 * String representation of random.
	 */
	@Override
	public String toString() {

		return "sample(" + this.getOperandsString() + ")";
	}

	/**
	 * Return a sample of the given population, with the size provided as last
	 * argument. If there is no arguments, or the sample size is larger than the
	 * population, the result is undefined.
	 */
	@Override
	public XObject eval() {

		if (this.getOperands().length < 1) {
			return Undef.UNDEF;
		}

		int length = this.getOperands().length;
		int size = this.getOperands()[length - 1].eval().toNumber().intValue();

		if (length - 1 < size) {
			return Undef.UNDEF;
		}
		
		ArrayList<Expression> population = new ArrayList<Expression>(Arrays
				.asList(this.getOperands()).subList(0, length - 1));

		// Ok, looks like we have a valid statement. Let's sample...
		Collections.shuffle(population);

		return new XList(population.subList(0, size));
	}

	/**
	 * Return false if eval's to false.
	 */
	@Override
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
