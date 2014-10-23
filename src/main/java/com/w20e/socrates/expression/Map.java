package com.w20e.socrates.expression;



public class Map extends AbstractFunctionImpl {

	public String toString() {

	    final StringBuffer buff = new StringBuffer("map(");
		buff.append(this.getOperands()[0].toString());
		buff.append(", {");
        for (int i = 1; i + 1 < this.getOperands().length; i += 2) {
            buff.append(this.getOperands()[i].toString());
            buff.append('=');
            buff.append(this.getOperands()[i + 1].toString());
            buff.append(", ");            
        }
        buff.append("})");
        
        return buff.toString();
	}

	/**
	 * Evaluate given summation.
	 */
	public XObject eval() {

        try {

            final String var = this.getOperands()[0].eval().toString();

            for (int i = 1; i + 1 < this.getOperands().length; i += 2) {
                if (this.getOperands()[i].toString().equals(var)) {
                    return this.getOperands()[i + 1].eval();
                }
            }
		} catch (Exception e) {
	        return Undef.UNDEF;
		}

        return Undef.UNDEF;
	}

	/**
	 * Boolean representation of this summation.
	 */
	public boolean toBoolean() {

		return eval().toBoolean();
	}

}
