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

import java.util.Locale;

import com.w20e.socrates.data.LexicalTransformation;
import com.w20e.socrates.data.Transformation;
import com.w20e.socrates.expression.XObject;
import com.w20e.socrates.expression.XString;


/**
 * @author dokter
 */
public class Format implements Transformation, LexicalTransformation {

  /**
   * Hold format string.
   */
  private String format;

  /**
   * Create a new format, using the given string as the format string.
   * @param formatString format string.
   */
  public Format(final String formatString) {

    this.format = formatString;
  }

  /**
   * Transform the object given the format string.
   * @param obj object to transform.
   * @return string representation of this object, according to the format
   * string.
   */
  @Override
public final Object transform(final Object obj) {

    return String.format(this.format, new Object[] {obj});
  }

  /**
   * Transform the object given the format string, and the locale.
   * @param obj object to transform.
   * @param l locale to use for transformation.
   * @return string representation of this object, according to the format
   * string.
   */
  @Override
public final Object transform(final Object obj, final Locale l) {

    return String.format(l, this.format, new Object[] {obj});
  }

  
  /**
   * Transform the object given the format string.
   * @param obj object to transform.
   * @return string representation of this object, according to the format
   * string.
   */
  @Override
public final XObject transform(final XObject obj, Locale l) {

    return new XString(String.format(l, this.format, new Object[] {obj.toNumber()}));
  }
}
