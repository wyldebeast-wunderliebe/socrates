package com.w20e.socrates.rendering;

import java.util.Set;

/**
 * Provide map of disable/enable settings for rendering.
 * @author dokter
 *
 */
public interface RenderOptions {

	/**
	 * Is the option enabled?
	 * @return
	 */
	boolean isEnabled(String option, boolean defaultValue);

	/**
	 * Is the option enabled?
	 * @return
	 */
	boolean isEnabled(String option);
	
	/**
	 * Is the option disabled?
	 * @param option
	 * @return
	 */
	boolean isDisabled(String option, boolean defaultValue);

		/**
	 * Is the option disabled?
	 * @param option
	 * @return
	 */
	boolean isDisabled(String option);

	/**
	 * Get all keys.
	 */
	Set<String> keys();
	
	/**
	 * Get value for key.
	 * @param key
	 * @return
	 */
	Object get(String key);
}
