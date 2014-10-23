package com.w20e.socrates.expression;

public class Str extends AbstractUnaryOperationImpl {

    @Override
    public String toString() {

        return "str(" + this.getLeftOperand().toString() + ")";
    }

    @Override
    public XObject eval() {

        return new XString(this.getLeftOperand().toString());
    }

    @Override
    public boolean toBoolean() {

        return this.eval().toBoolean();
    }

}
