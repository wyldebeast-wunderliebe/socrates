package com.w20e.socrates.expression;

public class Num extends AbstractUnaryOperationImpl {

    @Override
    public String toString() {

        return "num(" + this.getLeftOperand().toString() + ")";
    }

    @Override
    public XObject eval() {

    	return new XNumber(this.getLeftOperand().eval().toNumber());
    }

    @Override
    public boolean toBoolean() {

        return this.eval().toBoolean();
    }

}
