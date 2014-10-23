/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.model;


import java.util.Vector;

import org.apache.commons.jxpath.ri.Compiler;

import com.w20e.socrates.expression.And;
import com.w20e.socrates.expression.Ceil;
import com.w20e.socrates.expression.DateTime;
import com.w20e.socrates.expression.Divide;
import com.w20e.socrates.expression.Equals;
import com.w20e.socrates.expression.Eval;
import com.w20e.socrates.expression.Exp;
import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.Floor;
import com.w20e.socrates.expression.Format;
import com.w20e.socrates.expression.GT;
import com.w20e.socrates.expression.GTE;
import com.w20e.socrates.expression.IfThenElse;
import com.w20e.socrates.expression.In;
import com.w20e.socrates.expression.LT;
import com.w20e.socrates.expression.LTE;
import com.w20e.socrates.expression.Map;
import com.w20e.socrates.expression.Matches;
import com.w20e.socrates.expression.Max;
import com.w20e.socrates.expression.Min;
import com.w20e.socrates.expression.Minus;
import com.w20e.socrates.expression.Modulo;
import com.w20e.socrates.expression.Multiply;
import com.w20e.socrates.expression.Not;
import com.w20e.socrates.expression.NotEquals;
import com.w20e.socrates.expression.Num;
import com.w20e.socrates.expression.Operation;
import com.w20e.socrates.expression.Or;
import com.w20e.socrates.expression.RandomInt;
import com.w20e.socrates.expression.Round;
import com.w20e.socrates.expression.Sample;
import com.w20e.socrates.expression.Str;
import com.w20e.socrates.expression.Substr;
import com.w20e.socrates.expression.Sum;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.expression.XVar;

/**
 * @author dokter Implementation for JXPath compiler.
 */
public final class ExpressionCompiler implements Compiler {

	/**
	 * Instance for compiler.
	 */
	private static ExpressionCompiler instance = null;

	/**
	 * int value of parser for not function.
	 */
	private static final int NOT = 19;

	/**
	 * int value of parser for true function.
	 */
	private static final int TRUE = 20;

	/**
	 * int value of parser for rnd function.
	 */
	private static final int RND = 27;
	
	/**
	 * Sum id.
	 */
	private static final int SUM = 24;

	/**
     * Floor id.
     */
    private static final int FLOOR = 25;

	/**
	 * Hide constructor.
	 */
	private ExpressionCompiler() {
		// Empty constructor.
	}

	/**
	 * Get an instance of this compiler.
	 * 
	 * @return the instance.
	 */
	public static synchronized ExpressionCompiler getInstance() {

		if (ExpressionCompiler.instance == null) {
			ExpressionCompiler.instance = new ExpressionCompiler();
		}

		return ExpressionCompiler.instance;
	}

	/**
	 * Implement number creation.
	 * 
	 * @return an XNumber object
	 * @param arg0
	 *            numeric value
	 */
	public Object number(final String arg0) {

		if (arg0.indexOf('.') == -1) {
			return new XNumber(Long.valueOf(arg0));
		}
		return new XNumber(new Float(arg0));
	}

	/**
	 * Implement to literal for compiler.
	 * 
	 * @return XString value
	 * @param arg0
	 *            string
	 */
	public Object literal(final String arg0) {

		return new XString(arg0);
	}

	/**
	 * Implement qname method.
	 * 
	 * @param arg0
	 *            name
	 * @param arg1
	 *            dunno
	 * @return arg1
	 */
	public Object qname(final String arg0, final String arg1) {

		return arg1;
	}

	/**
	 * Implement summation creation.
	 * 
	 * @param arg0
	 *            summation operands
	 * @return summation operation
	 */
	public Object sum(final Object[] arg0) {

		return sum(0, arg0);
	}

	/**
	 * Enable multiple arguments to our two-operand summation.
	 * 
	 * @param i
	 *            pointer in argument array
	 * @param arg0
	 *            operands
	 * @return Sum expression
	 */
	private Expression sum(final int i, final Object[] arg0) {

		Sum op = new Sum();

		Vector<Expression> args = new Vector<Expression>();

		for (Object arg: arg0) {
		    
		    args.add((Expression) arg);
		}
				
		op.setOperands(args.toArray(new Expression[args.size()]));
		
		return op;
	}

	/**
	 * Implement minus creation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new minus operation
	 */
	public Object minus(final Object arg0, final Object arg1) {

		Operation op = new Minus();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Implement multiplication creation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return multiply operation
	 */
	public Object multiply(final Object arg0, final Object arg1) {

		Operation op = new Multiply();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Implement divide creation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return division operation
	 */
	public Object divide(final Object arg0, final Object arg1) {

		Operation op = new Divide();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new Module operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new module operation
	 */
	public Object mod(final Object arg0, final Object arg1) {

		Operation op = new Modulo();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new LT operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new LT operation
	 */
	public Object lessThan(final Object arg0, final Object arg1) {

		Operation op = new LT();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new LTE operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new LTE operation
	 */
	public Object lessThanOrEqual(final Object arg0, final Object arg1) {

		Operation op = new LTE();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new GT operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new GT operation
	 */
	public Object greaterThan(final Object arg0, final Object arg1) {

		Operation op = new GT();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new GTE operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new GTE operation
	 */
	public Object greaterThanOrEqual(final Object arg0, final Object arg1) {

		Operation op = new GTE();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new Equals operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new Equals operation
	 */
	public Object equal(final Object arg0, final Object arg1) {

		Operation op = new Equals();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create noteqals operation.
	 * 
	 * @param arg0
	 *            left operand
	 * @param arg1
	 *            right operand
	 * @return new notequals operation
	 */
	public Object notEqual(final Object arg0, final Object arg1) {

		Operation op = new NotEquals();
		op.setLeftOperand((Expression) arg0);
		op.setRightOperand((Expression) arg1);

		return op;
	}

	/**
	 * Create new Minus operation.
	 * 
	 * @param arg0
	 *            left operand for minus.
	 * @return new Unary minus
	 */
	public Object minus(final Object arg0) {

		Operation op = new Minus();
		op.setLeftOperand(new XNumber(Integer.valueOf(0)));
		op.setRightOperand((Expression) arg0);
		
		return op;
	}

	/**
	 * Unimplemented operation.
	 * 
	 * @param arg0
	 *            dunno
	 * @return null
	 */
	public Object variableReference(final Object arg0) {

		return null;
	}

	/**
	 * Only implemented to return true/false functions.
	 * 
	 * @param arg0
	 *            numeric argument specifying function
	 * @param arg1
	 *            dunno
	 * @return new XBoolean
	 */
	public Object function(final int arg0, final Object[] arg1) {

	    //System.out.print(arg0);
	    
		if (arg0 == ExpressionCompiler.TRUE) {
			return new XBoolean(true);
		} else if (arg0 == ExpressionCompiler.RND) {
			Round rnd = new Round();
            Vector<Expression> v = new Vector<Expression>();
            if (arg1 != null) {
                for (Object arg: arg1) {
                    v.add((Expression) arg);
                }
            }
            rnd.setOperands(v.toArray(new Expression[v.size()]));
			return rnd;
        }  else if (arg0 == ExpressionCompiler.FLOOR) {
            Floor floor = new Floor();
            floor.setLeftOperand((Expression) arg1[0]);
            return floor;
		} else if (arg0 == ExpressionCompiler.NOT) {
			Not not = new Not();
			not.setLeftOperand((Expression) arg1[0]);
			return not;
	     } else if (arg0 == ExpressionCompiler.SUM) {
	            return this.sum(0, arg1);  
		} else if (arg0 == Compiler.FUNCTION_STRING) {
			// only with mandatory parameter is implemented!
			return new XVar(arg1[0].toString()); // throws exception if args1
													// == null
		} else {
			return new XBoolean(false);
		}
	}

	/**
	 * Create the named function.
	 * 
	 * @param arg0
	 *            function name
	 * @param arg1
	 *            arguments
	 * @return the function or false if unknown.
	 */
	public Object function(final Object arg0, final Object[] arg1) {

	    //System.out.println("2 " + arg0);
	    //System.out.println(arg1);

		if (arg0.toString().equals("exp")) {
			Exp exp = new Exp();
			exp.setLeftOperand((Expression) arg1[0]);
			return exp;
		} else if (arg0.toString().equals("max")) {
			Max exp = new Max();
			exp.setLeftOperand((Expression) arg1[0]);
			exp.setRightOperand((Expression) arg1[1]);
			return exp;
		} else if (arg0.toString().equals("min")) {
			Min exp = new Min();
			exp.setLeftOperand((Expression) arg1[0]);
			exp.setRightOperand((Expression) arg1[1]);
			return exp;
		} else if (arg0.toString().equals("eval")) {
			return new Eval(arg1[0].toString());
		} else if (arg0.toString().equals("matches")) {
			Matches exp = new Matches();
			exp.setLeftOperand((Expression) arg1[0]);
			exp.setRightOperand((Expression) arg1[1]);
			return exp;
		} else if (arg0.toString().equals("datetime")) {
		    DateTime datetime = new DateTime();
            Vector<Expression> v = new Vector<Expression>();
            if (arg1 != null) {
                for (Object arg: arg1) {
                    v.add((Expression) arg);
                }
            }
            datetime.setOperands(v.toArray(new Expression[v.size()]));
            return datetime;
		} else if (arg0.toString().equals("test")
				|| arg0.toString().equals("if")) {

			IfThenElse exp = new IfThenElse();
			exp.setLeftOperand((Expression) arg1[0]);
			exp.setMiddleOperand((Expression) arg1[1]);
			exp.setRightOperand((Expression) arg1[2]);
			return exp;
		} else if (arg0.toString().equals("map")) {
			Map map = new Map();
            Vector<Expression> v = new Vector<Expression>();
            for (Object arg: arg1) {
                v.add((Expression) arg);
            }
            map.setOperands(v.toArray(new Expression[v.size()]));
            return map;
		}  else if (arg0.toString().equals("str")) {
		    Str str = new Str();
            str.setLeftOperand((Expression) arg1[0]);
            return str;
        }  else if (arg0.toString().equals("num")) {
            Num num = new Num();
            num.setLeftOperand((Expression) arg1[0]);
            return num;
        }  else if (arg0.toString().equals("ceil")) {
            Ceil ceil = new Ceil();
            ceil.setLeftOperand((Expression) arg1[0]);
            return ceil;
        }  else if (arg0.toString().equals("format")) {
            Format fmt = new Format();
            Vector<Expression> v = new Vector<Expression>();
            for (Object arg: arg1) {
                v.add((Expression) arg);
            }
            fmt.setOperands(v.toArray(new Expression[v.size()]));
            return fmt;
        }  else if (arg0.toString().equals("substr")) {
            Substr sstr = new Substr();
            Vector<Expression> v = new Vector<Expression>();
            for (Object arg: arg1) {
                v.add((Expression) arg);
            }
            sstr.setOperands(v.toArray(new Expression[v.size()]));
            return sstr;
        } else if (arg0.toString().equals("random")) {
            RandomInt r = new RandomInt();
            Vector<Expression> v = new Vector<Expression>();
            for (Object arg: arg1) {
                v.add((Expression) arg);
            }
            r.setOperands(v.toArray(new Expression[v.size()]));
            return r;
        } else if (arg0.toString().equals("sample")) {
            Sample s = new Sample();
            Vector<Expression> v = new Vector<Expression>();
            for (Object arg: arg1) {
                v.add((Expression) arg);
            }
            s.setOperands(v.toArray(new Expression[v.size()]));
            return s;
        } else if (arg0.toString().equals("in")) {
            In in = new In();
            Vector<Expression> v = new Vector<Expression>();
            for (Object arg: arg1) {
                v.add((Expression) arg);
            }
                        
            in.setOperands(v.toArray(new Expression[v.size()]));
            
            return in;
        }

		return new XBoolean(false);
	}

	/**
	 * Create new and object.
	 * 
	 * @param arg0
	 *            left and right operands.
	 * @return new and object.
	 */
	public Object and(final Object[] arg0) {
		return and(0, arg0);
	}

	/**
	 * Enable multiple arguments to our two-operand summation.
	 * 
	 * @param i
	 *            pointer in argument array
	 * @param arg0
	 *            operands
	 * @return Sum expression
	 */
	private Expression and(final int i, final Object[] arg0) {

		Operation op = new And();

		if (arg0.length <= i) {
			return op;
		}

		op.setLeftOperand((Expression) arg0[i]);

		if (arg0.length == i + 1) {
			return op;
		} else if (arg0.length == i + 2) {
			op.setRightOperand((Expression) arg0[i + 1]);
		} else {
			op.setRightOperand(and(i + 1, arg0));
		}

		return op;
	}

	/**
	 * Create new or object.
	 * 
	 * @param arg0
	 *            left and right operands.
	 * @return new or object.
	 */
	public Object or(final Object[] arg0) {

		return or(0, arg0);
	}

	/**
	 * Enable multiple arguments to our two-operand summation.
	 * 
	 * @param i
	 *            pointer in argument array
	 * @param arg0
	 *            operands
	 * @return Sum expression
	 */
	private Expression or(final int i, final Object[] arg0) {

		Operation op = new Or();

		if (arg0.length <= i) {
			return op;
		}

		op.setLeftOperand((Expression) arg0[i]);

		if (arg0.length == i + 1) {
			return op;
		} else if (arg0.length == i + 2) {
			op.setRightOperand((Expression) arg0[i + 1]);
		} else {
			op.setRightOperand(or(i + 1, arg0));
		}

		return op;
	}

	/**
	 * Create new union object.
	 * 
	 * @param arg0
	 *            left and right operands.
	 * @return new union object.
	 */
	public Object union(final Object[] arg0) {

		return or(arg0);
	}

	/**
	 * Create new node name test. Unimplemented!
	 * 
	 * @param arg0
	 *            dunno
	 * @return atg0.
	 */
	public Object nodeNameTest(final Object arg0) {

		return arg0;
	}

	/**
	 * Create new nodetype test. Unimplemented!
	 * 
	 * @param arg0
	 *            dunno
	 * @return null.
	 */
	public Object nodeTypeTest(final int arg0) {

		return null;
	}

	/**
	 * Create new processing instruction test. Unimplemented!
	 * 
	 * @param arg0
	 *            dunno
	 * @return null
	 */
	public Object processingInstructionTest(final String arg0) {

		return null;
	}

	/**
	 * Create new step. Unimplemented!
	 * 
	 * @param arg0
	 *            dunno
	 * @param arg1
	 *            dunno
	 * @param arg2
	 *            dunno
	 * @return step object
	 */
	public Object step(final int arg0, final Object arg1, final Object[] arg2) {

		// System.out.println("Step " + arg1 + ", " + arg2.length);
		return arg1;
	}

	/**
	 * Create a location path. Creates an XRef object.
	 * 
	 * @param arg0
	 *            starts at root?
	 * @param arg1
	 *            elements of the path
	 * @return a new XRef object
	 */
	public Object locationPath(final boolean arg0, final Object[] arg1) {

		String res = "";

		for (int i = 0; i < arg1.length; i++) {
			res = res + "/" + arg1[i];
		}

		return new XVar(res);
	}

	/**
	 * Create a new expression path. For now unimplemented.
	 * 
	 * @param arg0
	 *            dunno
	 * @param arg1
	 *            dunno
	 * @param arg2
	 *            dunno
	 * @return expressionpath object.
	 */
	public Object expressionPath(final Object arg0, final Object[] arg1,
			final Object[] arg2) {

		// System.out.println("exp: " + arg0);
		return arg0;
	}
}
