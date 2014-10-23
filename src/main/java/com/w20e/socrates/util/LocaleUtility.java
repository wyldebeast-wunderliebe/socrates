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

package com.w20e.socrates.util;

import java.util.Locale;


/**
 * @author dokter
 * Utility class for creating locale from locale strings, based on a
 * locale string of 5 characters, like 'nl_NL' or nl_NL_informal.
 */
public final class LocaleUtility {

  /**
   * Default locale. en_GB, why not..?
   */
  public static final Locale DEFAULT_LOCALE = new Locale("en", "GB");

  /**
   * Privatize constructor.
   */
  private LocaleUtility() {
	  // Empty constructor.
  }

  /**
   * Get the locale for the given string. If the string is not of length
   * COUNTRY_END, return null.
   * @param localeStr String of 5 chars, specifying locale.
   * @return the locale found.
   */
  public static Locale getLocale(final String localeStr, final boolean useDefault) {

	  if (localeStr == null || "".equals(localeStr)) {
    	if (useDefault) {
    	    return LocaleUtility.DEFAULT_LOCALE;
    	}
    	return null;
	  }

	  String[] parts = localeStr.split("_");
	  
	  if (parts.length == 1) {
		  return new Locale(parts[0]);
	  } else if (parts.length == 2) {
		  return new Locale(parts[0], parts[1]);
	  }
		  
	  return new Locale(parts[0], parts[1], parts[2]);
  }
}
