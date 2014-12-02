package com.w20e.socrates.rendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.w20e.socrates.data.Node;


/**
 * RenderableImpl provides a basic implementation for anything that can be used
 * for the rendering framework.
 * @author dokter
 */
public abstract class RenderableImpl implements Renderable {

	/**
     * Default serial id.
     */
    private static final long serialVersionUID = 1L;

	/**
	 * Hold unique id for this item.
	 */
	private String id;
	private Map<String, String>props;
	
	public RenderableImpl() {

        this.props = new HashMap<String, String>();
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * Constructor for Renderable item. An id should always be there.
	 * @param newId
	 */
	public RenderableImpl(String newId) {
		
        this.props = new HashMap<String, String>();
		this.id = newId;
	}

	/**
	 * Return the item's id.
	 */
	public String getId() {

		return this.id;
	}
	
	/**
	 * Return the item's id.
	 */
	public void setId(String newId) {

		this.id = newId;
	}

	public String toString() {
		
		return getType() + "[@id=" + getId() + "]";
	}
	
	public String getProperty(String key, String defaultValue) {
		
		if (this.props.containsKey(key)) {
			return this.props.get(key);			
		}

		return defaultValue;
	}

	public String getProperty(String key) {
		
		return this.props.get(key);
	}

	public String setProperty(String key, String value) {
		
		return this.props.put(key, value);
	}
}
