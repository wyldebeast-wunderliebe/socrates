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

package com.w20e.socrates.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.expression.XObject;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.expression.XList;

/**
 * @author dokter Implementation class for DataType. The data type can provide
 *         values in a number of conceptual 'spaces'. The lexical space value
 *         for instance could be something completely different from the value
 *         space. For example, currency would usually be depicted as two digits
 *         behind the comma, whereas the value space would really be a float.
 * @todo Actually all of these classes could be singletons. Or at least the
 *       transformations and restrictions...
 */
public abstract class DataTypeImpl implements DataType {

	/**
	 * Hold all restrictions.
	 */
	private final List<Restriction> restrictions = new ArrayList<Restriction>();

	/**
	 * Hold all transformations.
	 */
	private final List<Transformation> transformations = new ArrayList<Transformation>();

	/**
	 * Hold all lexical transformations.
	 */
	private final List<LexicalTransformation> lexTransformations = new ArrayList<LexicalTransformation>();

	/**
	 * Evaluate against all restrictions. The method will throw a runtime
	 * exception in case the given object cannot be evaluated to the type. The
	 * special case when the value is null is handled as follows: whenever the
	 * type implementation doesn't handle this case specifically, we just
	 * proceed. In case a NullPointerException is thrown, Undef is returned. This
	 * way, it is up to the user to decide what to do with that...
	 * 
	 * @param value value to check and transform.
	 * @return The XObject after evaluation of all transformations and
	 * restrictions.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public final XObject eval(final Object value)
	    throws TransformationException, RestrictionViolation {

		Object newValue = value;
		
		for (final Iterator<Transformation> i = this.transformations.iterator(); i.hasNext();) {
			newValue = i.next().transform(newValue);
		}

		for (final Iterator<Restriction> i = this.restrictions.iterator(); i.hasNext();) {
			if (! i.next().eval(newValue)) {
			    throw new RestrictionViolation("Value not conform restriction");
			}
		}
		
		if (newValue == null) {
			return new Undef();
		} else if (newValue instanceof Number) {
			return new XNumber((Number) newValue);
		} else if (newValue instanceof Boolean) {
			return new XBoolean((Boolean) newValue);
		} else if (newValue instanceof List) {
			return new XList((List) newValue);
		}

		return new XString(newValue.toString());
	}

	/**
     * Validate against all restrictions. The method will throw a runtime
     * exception in case the given object cannot be evaluated to the type. The
     * special case when the value is null is handled as follows: whenever the
     * type implementation doesn't handle this case specifically, we just
     * proceed. In case a NullPointerException is thrown, Undef is returned. This
     * way, it is up to the user to decide what to do with that...
     * 
     * @param value value to check.
     * @return The XObject after evaluation of all restrictions.
     */
    @Override
	public final void validate(final Object value)
        throws RestrictionViolation {

        for (final Iterator<Restriction> i = this.restrictions.iterator(); i.hasNext();) {
            if (! i.next().eval(value)) {
                throw new RestrictionViolation("Value not conform restriction");
            }
        }
    }

	/**
	 * Get the lexical representation for this type, given the locale. This is
	 * always a string representation.
	 * 
	 * @param value
	 *            Original value
	 * @param l
	 *            locale to use in transformation.
	 * @return lexical value
	 * @TODO move null check and return of "" to lexical transformations.
	 */
	@Override
	public final String evalLexical(final Object value, final Locale locale)
	throws TransformationException, RestrictionViolation {

		XObject newValue = eval(value);

		for (LexicalTransformation t : this.lexTransformations) {
			newValue = t.transform(newValue, locale);
		}

		return newValue.toString();
	}

	/**
	 * Add a restriction to this type.
	 * 
	 * @param restriction
	 *            new restriction.
	 */
	@Override
	public final void addRestriction(final Restriction restriction) {

		this.restrictions.add(restriction);
	}

	/**
	 * Add a transformation to the type, for manipulating objects.
	 * 
	 * @param trans
	 *            the transformation to add.
	 */
	@Override
	public final void addTransformation(final Transformation trans) {

		this.transformations.add(trans);
	}

	/**
	 * Set the lexical transformation for this type. This should consider the
	 * given locale. Default is no transformation at all.
	 * 
	 * @param trans
	 *            the transformation to add.
	 */
	@Override
	public final void addLexicalTransformation(
			final LexicalTransformation ltrans) {

		this.lexTransformations.add(ltrans);
	}
}
