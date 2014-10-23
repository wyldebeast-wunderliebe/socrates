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
public class Substr extends AbstractFunctionImpl {

    @Override
    public String toString() {
        
        return "substr(" + this.getOperandsString() + ")";
    }

    @Override
    public XObject eval() {

        if (this.getOperands().length == 0) {
            return Undef.UNDEF;
        }
        
        final List<Number> args = new ArrayList<Number>();

        for (int i = 1; i < this.getOperands().length; i++) {
            args.add(this.getOperands()[i].eval().toNumber());
        }

        int start = 0;
        int end = this.getOperands()[0].toString().length();
        
        String orig = this.getOperands()[0].toString();

        if (!args.isEmpty()) {
            start = args.get(0).intValue();
        }

        if (args.size() > 1) {
            end = args.get(1).intValue();
        }

        if (start > orig.length()) {
        	return new XString("");
        }
        
        if (end > orig.length()) {
        	end = orig.length();
        }
        
        try {
        	return new XString(orig.substring(start, end));
        } catch (Exception e) {
        	return Undef.UNDEF;
        }
    }

    @Override
    public boolean toBoolean() {

        return this.eval().toBoolean();
    }

}
