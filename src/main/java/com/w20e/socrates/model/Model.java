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

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * <code>Model</code> is used as an interface to one or more <code>Item</code>
 * objects. This interface is designed for use in flyweight patterns: it is
 * stateless. Therefore we always need to provide the Instance to methods that
 * would need instance data. This is a deviation from the usual XForms model.
 * </p>
 * <p>
 * The Model provides methods to obtain ItemProperties, or 'Binds'.
 * </p>
 * 
 * @author helmantel
 */
public interface Model {

	/**
	 * Set the model's id.
	 * 
	 * @param id
	 *            Model's id
	 */
	void setId(String modeId);

	/**
	 * Get the model's id.
	 * 
	 * @return id
	 */
	String getId();

	/**
	 * Get all item properties.
	 * 
	 * @return complete list of available items.
	 */
	Collection<ItemProperties> getAllItemProperties();

	/**
	 * Get an Item identified by it's bind id.
	 * 
	 * @param itemId
	 *            Id of the item properties.
	 * @return an <code>ItemProperties</code> object, <b>null </b> if nothing
	 *         found.
	 */
	ItemProperties getItemProperties(String itemId);

	/**
	 * Set the submission for this model.
	 * 
	 * @param s
	 *            Submission implementation
	 */
	void setSubmission(Submission submission);

	/**
	 * Get the submission information for this Model.
	 * 
	 * @return the submission info for the model.
	 */
	Submission getSubmission();

	/**
	 * Return the meta data for this model.
	 * 
	 * @return Meta data information
	 */
	Map<String, String> getMetaData();
}
