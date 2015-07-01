package com.w20e.socrates.expression;

import java.util.Arrays;

public class Get extends AbstractFunctionImpl {

	@Override
	public String toString() {

		return "get(" + this.getOperandsString() + ")";
	}

	/**
	 * Evaluate get to object.
	 */
	@Override
	public XObject eval() {
		
		if (this.getOperands().length < 2) {
			return Undef.UNDEF;
		}
		
		XList list;
		
		if (!(this.getOperands()[1].eval() instanceof XList)) {
			list = new XList(Arrays.asList(this.getOperands()).subList(1, 
					this.getOperands().length));
		} else {
			list = (XList)this.getOperands()[1].eval();
		}
		
		try {
			return list.get(this.getOperands()[0].eval().toNumber().intValue()).eval();
		} catch (Exception ex) {
			return Undef.UNDEF;
		}
	}

	/**
	 * Boolean representation of this get.
	 */
	@Override
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
