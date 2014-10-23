package com.w20e.socrates.util;

import java.util.Locale;

public interface UTF8ResourceBundle {

	/**
	 * Return the translation for key, using the default if not found.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getString(String key, String defaultValue);

	/**
	 * Get value for given key.
	 * @param key
	 * @return
	 */
	public String getString(String key);

	/**
	 * Get locale for this bundle.
	 * @return
	 */
	public Locale getLocale();
}
