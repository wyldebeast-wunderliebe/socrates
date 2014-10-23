package com.w20e.socrates.expression;

import java.util.Arrays;

public class In extends AbstractFunctionImpl {

	public String toString() {

		return "in(" + this.getOperandsString() + ")";
	}

	/**
	 * Evaluate given summation.
	 */
	public XObject eval() {
		
		if (this.getOperands().length < 2) {
			return Undef.UNDEF;
		}
		
		XList list;
		
		if (!(this.getOperands()[1].eval() instanceof XList)) {
			System.out.println("its not a list, it's a " + this.getOperands()[1].getClass());
			list = new XList(Arrays.asList(this.getOperands()).subList(1, 
					this.getOperands().length));
		} else {
			System.out.println("its a list!");
			list = (XList)this.getOperands()[1].eval();
		}

		System.out.println("LIST" + list);
		
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
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
