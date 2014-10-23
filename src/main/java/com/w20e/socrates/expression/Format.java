package com.w20e.socrates.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dokter
 * Format function. The function takes a format string as first argument, and the
 * items to format accordingly as other arguments. All format arguments are
 * 'eval'-ed first, than the toObject method is called to get the raw java Object.
 * This object is passed to the format function. 
 */
public class Format extends AbstractFunctionImpl {

    @Override
    public String toString() {
        
        return "format(" + this.getOperandsString() + ")";
    }

    @Override
    public XObject eval() {

        final List<Object> args = new ArrayList<Object>();

        for (int i = 1; i < this.getOperands().length; i++) {
            args.add(this.getOperands()[i].eval().toObject());
        }

        try {
        	return new XString(String.format(this.getOperands()[0].toString(),
        			args.toArray()));
        } catch (Exception e) {
        	return Undef.UNDEF;
        }
    }

    @Override
    public boolean toBoolean() {

        return this.eval().toBoolean();
    }

}
