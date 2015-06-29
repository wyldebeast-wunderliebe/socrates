package com.w20e.socrates.expression;

import java.util.Arrays;

public class In extends AbstractFunctionImpl {

	@Override
	public String toString() {

		return "in(" + this.getOperandsString() + ")";
	}

	/**
	 * Evaluate given summation.
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
		
		boolean isIn = false;
		
        for (Expression expr: list) {
            if (expr.toString().equals(this.getOperands()[0].toString())) {
                isIn = true;
                break;
            }
        }
        
        return new XBoolean(isIn);
	}

	/**
	 * Boolean representation of this summation.
	 */
	@Override
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
