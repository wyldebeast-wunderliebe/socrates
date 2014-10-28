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

package com.w20e.socrates.workflow;

import java.util.Properties;

/**
 * Process action implementation.
 * 
 * @author <a href="mailto:dokter@w20e.com">Duco Dokter</a>
 * @version $Revision: 1.8 $
 */
public abstract class AbstractProcessActionImpl implements ProcessAction {

	/**
	 * Id holder.
	 */
	private final String actionId;

	/**
	 * Container for properties.
	 */
	private Properties properties;

	/**
	 * Creates a new <code>ProcessActionImpl</code> instance with the given id.
	 * 
	 * @param actionId
	 *            a <code>String</code> value
	 */
	public AbstractProcessActionImpl(final String newActionId) {

		this.actionId = newActionId;
		this.properties = new Properties();
	}

	/**
	 * All actions should have a unique ID within a processor.
	 * 
	 * @return a <code>String</code> value
	 */
	@Override
	public final String getId() {

		return this.actionId;
	}

	/**
	 * Get property by name.
	 */
	@Override
	public String getProperty(String key) {

		return this.properties.getProperty(key);
	}

	/**
	 * Set property on action.
	 */
	@Override
	public void setProperty(final String key, final String value) {

		this.properties.setProperty(key, value);
	}
}
