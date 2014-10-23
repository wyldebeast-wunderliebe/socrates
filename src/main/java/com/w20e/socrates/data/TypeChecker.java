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

import java.util.HashMap;
import java.util.Locale;

import com.w20e.socrates.data.DataType;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XObject;

/**
 * Type checker class providing flyweight use of datatypes.
 * @author dokter
 *
 */
public final class TypeChecker {

  /**
   * Cache for types.
   */
  private static HashMap<Class<?>, DataType> typeCache;

  /**
   * Static initializing block.
   */
  static {
    TypeChecker.typeCache = new HashMap<Class<?>, DataType>();
  }

  /**
   * Privatize constructor.
   */
  private TypeChecker() {
	  // Empty constructor.
  }

  /**
   * Clear internal cache for types.
   */
  public static void clear() {
	  TypeChecker.typeCache.clear();
  }
  
  /**
   * Evaluate object against type.
   * @param type type for object
   * @param value value
   * @return new evaluated object or undefined if the transformation cannot be done.
 * @throws RestrictionViolation 
 * @throws TransformationException 
   */
  public static XObject evaluate(final Class<?> type, final Object value) {

    if (!TypeChecker.typeCache.containsKey(type)) {
        try {
            TypeChecker.typeCache.put(type, (DataType) type.newInstance());
        } catch (Exception e) {
        	// No need to catch this, will fail later on...
        }
    }

    try {
        return TypeChecker.typeCache.get(type).eval(value);
    } catch (Exception e) {
        return Undef.UNDEF;
    }
  }

  /**
   * Evaluate object against type.
   * @param type type for object
   * @param value value
   * @return new evaluated object
 * @throws RestrictionViolation 
 * @throws TransformationException 
   */
  public static void validate(final Class<?> type, final Object value)
  throws RestrictionViolation {

    if (!TypeChecker.typeCache.containsKey(type)) {
        try {
            TypeChecker.typeCache.put(type, (DataType) type.newInstance());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    TypeChecker.typeCache.get(type).validate(value);
  }
    
  /**
   * Evaluate to lexical value.
   * @param type data type
   * @param value value
   * @return The lexical value
 * @throws RestrictionViolation 
 * @throws TransformationException 
   */
  public static Object evaluateLexical(final Class<?> type, final Object value,
      Locale l) throws TransformationException, RestrictionViolation {

    if (!TypeChecker.typeCache.containsKey(type)) {
      try {
        TypeChecker.typeCache.put(type, (DataType) type.newInstance());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    return TypeChecker.typeCache.get(type).evalLexical(value, l);
  }
}
