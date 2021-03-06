/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : ModelImpl.java
 * Classname : ModelImpl
 * Author    : Wietze Helmantel
 * Date      : 15 Jan 2005
 * Version   : $Revision: 1.43 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Default implementation of a Model as found in the Socrates QE model. The
 * Model is basically a container for ItemProperties, and a factory for Items.
 * Items in this implementation are always created on-the-fly. An Item is a
 * wrapper for a single Node and a <em>single</em> ItemProperties. The model is
 * implemented as singleton, to be able to use the model in a flyweight pattern,
 * so it doesn't hold a reference to an Instance.
 * </p>
 * <p>
 * Items may be created by referring to nodes from the instance, or to the id's
 * of item properties.
 * </p>
 * 
 * @todo make sure that when an item properties object is added, asking for a
 *       specific expression will fetch ALL properties, not only one.
 * @author W.G.Helmantel
 */
public class ModelImpl implements Model {

    /**
     * A Model really should have an id...
     */
    private String id;

    /**
     * Submission info container.
     */
    private Submission submission;

    /**
     * Map of ItemProperties objects, stored by their binds.
     */
    private Map<String, ItemProperties> propsMap = new HashMap<String, ItemProperties>();

    /**
     * Map of ItemProperties objects, stored by their id.
     */
    private Map<String, ItemProperties> propsById = new HashMap<String, ItemProperties>();

    
    public static ItemProperties DEFAULT_PROPERTIES = new ItemPropertiesImpl("default");
    
    /**
     * Hold meta data.
     */
    private Map<String, String> metadata = new HashMap<String, String>();

    /**
     * Set the model's id.
     * 
     * @param modelId
     *            Id for the model.
     */
    @Override
	public void setId(final String modelId) {

        this.id = modelId;
    }

    /**
     * Get the model's id.
     * 
     * @return model id
     */
    @Override
	public String getId() {

        return this.id;
    }

    /**
     * Return the ItemProperties defined by it's bind. If the properties object
     * can't be found, return a default implementation.
     * 
     * @param propsId
     *            bind id for the item properties.
     * @return the ItemProperties found, or null if none found.
     */
    @Override
	public ItemProperties getItemProperties(final String bind) {

    	if (this.propsMap.containsKey(bind)) {
        return this.propsMap.get(bind);
    	} else {
    		return DEFAULT_PROPERTIES;
    	}
    }

	public ItemProperties getItemPropertiesById(final String propsId) {

    	if (this.propsById.containsKey(propsId)) {
        return this.propsById.get(propsId);
    	} else {
    		return DEFAULT_PROPERTIES;
    	}
    }

    /**
     * Add an ItemProperties object. It's getReference method will be used to
     * retrieve the path Expression for binding.
     * 
     * @param ip
     *            the item properties obect to add.
     */
    public void addItemProperties(final ItemProperties ip) {
        if (ip != null) {

        	for (String bind: ip.getBind()) {
        		this.propsMap.put(bind, ip);
        		this.propsById.put(ip.getId(), ip);
        	}
        }	
    }

    /**
     * Get the submission for this Model.
     * 
     * @return a <code>Submission</code> value
     */
    @Override
	public Submission getSubmission() {

        return this.submission;
    }

    /**
     * Set the submission for this model.
     * 
     * @param sub
     *            Submission to use.
     */
    @Override
	public void setSubmission(final Submission sub) {

        this.submission = sub;
    }

    /**
     * Return the list of all ItemProperties (bind's) for this model.
     * 
     * @return all item properties.
     */
    @Override
	public Collection<ItemProperties> getAllItemProperties() {

        return this.propsMap.values();
    }

    /**
     * Return meta data for this model.
     * 
     * @return meta data as map
     */
    @Override
	public Map<String, String> getMetaData() {

        return this.metadata;
    }
}
