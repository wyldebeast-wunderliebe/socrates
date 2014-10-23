package com.w20e.socrates.expression;

public interface Function extends Expression {

	/**
	 * Set the function's arguments.
	 * 
	 * @param args
	 */
	Expression[] getOperands();
}
