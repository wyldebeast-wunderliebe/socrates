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

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * UTF-8 resource bundle loader. Many thanks to 'thoughts':
 * 'http://www.thoughtsabout.net/blog/archives/000044.html'
 * 
 * @author dokter
 */
public final class UTF8ResourceBundleImpl {

	/**
	 * Hide constructor.
	 */
	private UTF8ResourceBundleImpl() {
		// Empty constructor.
	}

	/**
	 * Get the bundle for base.
	 * 
	 * @todo Make this bundle actualy read in a file instead of loading classes.
	 * @param baseName
	 *            base
	 * @return the bundle if found.
	 */
	public static UTF8ResourceBundle getBundle(final String baseName) {

		ResourceBundle.clearCache();
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		return createUtf8PropertyResourceBundle(bundle);
	}

	/**
	 * Get the bundle for the locale and base.
	 * 
	 * @param baseName
	 *            base
	 * @param locale
	 *            locale
	 * @return the bundle if found.
	 */
	public static UTF8ResourceBundle getBundle(final String baseName,
			final Locale locale) {

		ResourceBundle.clearCache();
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		return createUtf8PropertyResourceBundle(bundle);
	}

	/**
	 * Get the bundle.
	 * 
	 * @param baseName
	 *            base name
	 * @param locale
	 *            locale
	 * @param loader
	 *            class loader to use.
	 * @return the bundle
	 */
	public static UTF8ResourceBundle getBundle(final String baseName,
			final Locale locale, final ClassLoader loader) {

		ResourceBundle.clearCache();
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale,
				loader);
		return createUtf8PropertyResourceBundle(bundle);
	}

	/**
	 * Create utf resource bundle.
	 * 
	 * @param bundle
	 *            native bundle
	 * @return the utf-8 bundle.
	 */
	private static UTF8ResourceBundle createUtf8PropertyResourceBundle(
			final ResourceBundle bundle) {

		if (!(bundle instanceof PropertyResourceBundle)) {
			throw new RuntimeException("Can only use property bundles");
		}

		return new Utf8PropertyResourceBundle((PropertyResourceBundle) bundle);
	}

	/**
	 * Inner class for utf bundle.
	 * 
	 * @author dokter
	 */
	private static final class Utf8PropertyResourceBundle extends
			ResourceBundle implements UTF8ResourceBundle {

		/**
		 * Hold property bundle.
		 */
		private PropertyResourceBundle bundle;

		/**
		 * Private constructor.
		 * 
		 * @param newBundle
		 *            property bundle
		 */
		Utf8PropertyResourceBundle(final PropertyResourceBundle newBundle) {

			this.bundle = newBundle;
		}

		/**
		 * Return all keys for this bundle.
		 * 
		 * @return keys
		 */
		@Override
		@SuppressWarnings("unchecked")
		public Enumeration getKeys() {

			return this.bundle.getKeys();
		}

		/**
		 * Override getString, to prevent nasty null pointer exceptions.
		 */
		@Override
		public String getString(final String key, final String defaultMsg) {
			try {
				return this.bundle.getString(key);
			} catch (Exception e) {
				return defaultMsg;
			}
		}
		
		/**
		 * Get the object, encode as UTF-8.
		 * 
		 * @param key
		 *            key
		 * @return value
		 */
		@Override
		protected Object handleGetObject(final String key) {

			String value = (String) this.bundle.handleGetObject(key);
			try {
				return new String(value.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// Shouldn't fail - but should we still add logging message?
				return null;
			}
		}

	}
}
