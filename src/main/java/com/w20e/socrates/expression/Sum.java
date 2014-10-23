package com.w20e.socrates.expression;

public class Sum extends AbstractFunctionImpl {

	public String toString() {

		return "sum(" + this.getOperandsString() + ")";
	}

	/**
	 * Evaluate given summation.
	 */
	public XObject eval() {
		
		double sum = 0;
		
		for (Expression e: this.getOperands()) {
			sum += e.eval().toNumber().doubleValue();
		}

		return new XNumber(Double.valueOf(sum));
	}

	/**
	 * Boolean representation of this summation.
	 */
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
