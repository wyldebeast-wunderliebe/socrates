package com.w20e.socrates.rendering;

import java.util.List;

/**
 * Provide an interface for rendering info. This is basically a container for
 * renderable items, should maintain order and hierarchy.
 * 
 * @author dokter
 * 
 */
public interface RenderConfig extends RenderableContainer {

	/**
	 * Get all RenderItem objects for this configuration.
	 * 
	 * @return collection of renderable items.
	 */
	List<Renderable> getItems();

	/**
	 * Get item by it's id.
	 */
	Renderable getItem(String itemId);
}
