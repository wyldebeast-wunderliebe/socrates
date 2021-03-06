package com.w20e.socrates.rendering;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * RenderableImpl provides a basic implementation for anything that can be used
 * for the rendering framework.
 * @author dokter
 */
public abstract class RenderableImpl implements Renderable {

	/**
     * Default serial id.
     */
    @SuppressWarnings("unused")
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

	/**
	 * Default impl returns classname lowercase
	 */
	public String getType() {

		return this.getClass().getSimpleName().toLowerCase();
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
