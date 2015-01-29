package com.w20e.socrates.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List of XObjects
 * @author dokter
 */
public class XList extends AbstractXObjectImpl implements Iterable<Expression> {

	/**
	 * UID.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	/**
	 * Wrapped list.
	 */
	private final List<Expression> members;

	/**
	 * Constructor taking a String object as argument. The XString class wraps
	 * the argument string with Expression methods.
	 * 
	 * @param string
	 *            the string to wrap.
	 */
	public XList(List<Expression> members) {

		super();
		this.members = members;
	}

	/**
	 * Constructor taking nothing
	 */
	public XList() {

		super();
		this.members = new ArrayList<Expression>();
	}

	/**
	 * String representation of this object.
	 * 
	 * @return the <code>java.lang.String</code> object wrapped by this class.
	 */
	@Override
	public final String toString() {

		return this.members.toString();
	}

	/**
	 * Get the Number object wrapped by this XString. If the value cannot be
	 * represented as a number, a Float representing NaN will be returned.
	 * 
	 * @return the wrapped <code>Number</code> object
	 */
	@Override
	public final Number toNumber() {

		return this.members.size();
	}

	/**
	 * The signature for this method is that it should return 0 if the
	 * expressions are equal, a value less than zero if the expression provided
	 * as an argument is greater than the expression, and a value bigger than
	 * zero if the expression provided as argument is less than the expression.
	 * If the argument is not an instance of Expression, a ClassCastException is
	 * thrown.
	 * 
	 * @param expr
	 *            The expression to use for comparison.
	 * @return integer value indicating result of comparison
	 */
	@Override
	public final int compareTo(final Expression expr) {

		if (expr == null) {
			return -1;
		}
		
		if (!(expr instanceof XList)) {
			return -1;
		}
		
		if (((XList) expr).toNumber().intValue() > this.toNumber().intValue()) {
			return -1;
		}

		if (((XList) expr).toNumber().intValue() < this.toNumber().intValue()) {
			return 1;
		}
		
		if (this.members.equals(((XList) expr).toObject())) {
			return 0;
		} else {
			return -1;
		}
	}	

	/**
	 * Compares this XObject to the specified object. If the argument is null,
	 * and the XObject is Undef, true will be returned. If either the XObject
	 * itself or the argument is an XBoolean, both will converted to boolean.
	 * Otherwise if one is an XNumber, both will be converted to an XNumber.
	 * Otherwise the comparison will be string wise.
	 * 
	 * @param obj
	 *            The expression to use in equality check.
	 * @return the boolean representation of this object.
	 */
	@Override
	public final boolean equals(final Object obj) {

		if (!(obj instanceof Expression)) {
			return false;
		}

		return this.compareTo((Expression) obj) == 0;
	}

	/**
	 * Returns true if and only if the String wrapped by this class is not null,
	 * and is not an empty string.
	 * 
	 * @return boolean representation of this object.
	 */
	@Override
	public final boolean toBoolean() {

		return this.members.size() > 0;
	}

	/**
	 * Return underlying object. This is always a String.
	 * 
	 * @return the wrapped String object
	 */
	@Override
	public final Object toObject() {

		return this.members;
	}

	/**
	 * Check whether the given expression is in this list.
	 * @param obj
	 * @return well, whether it is... or not.
	 */
	public final boolean contains(Expression obj) {
		
		return this.members.contains(obj);
	}
	
	@Override
	public Iterator<Expression> iterator() {
		
		return this.members.iterator();
	}
	
	public int size() {
		
		return this.members.size();
	}
}
