package com.w20e.socrates.rendering;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RenderableContainerImpl
extends RenderableImpl
implements RenderableContainer {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	/**
	 * Holds a list of items for this group.
	 */
	private LinkedHashMap<String, Renderable> items =
		new LinkedHashMap<String, Renderable>();

	public RenderableContainerImpl() {

		super();
	}

	public RenderableContainerImpl(String id) {

		super(id);
	}
	
	/**
	 * @return Returns the items contained by this group, in the order they were
	 *         added.
	 */
	@Override
	public List<Renderable> getItems() {

		return new ArrayList<Renderable>(items.values());
	}

	/**
	 * Add an item to the config.
	 * 
	 * @param item
	 *            The item to add.
	 */
	public final void addItem(final Renderable item) {

		this.items.put(item.getId(), item);
	}

	/**
	 * Return the renderable item by it's id. Note that this may also be a nested item.
	 * Search is performed depth first, LR.
	 * @param id
	 * @return
	 */
	public Renderable getItem(String id) {

		return getItemById(this, id);
	}

	/**
	 * Get the item by it's index.
	 * @param index
	 * @return Renderable
	 */
	public Renderable getItem(int index) {
		
		return this.getItems().get(index);
	}
	
	/**
	 * @todo Find something more efficient...
	 */
	private Renderable getItemById(RenderableContainer container, String id) {
		
		Renderable res = null;
		
		for (Renderable r : container.getItems()) {
						
			if (id.equals(r.getId())) {
				return r;
			}
			if (r instanceof RenderableContainer) {
				res = getItemById((RenderableContainer) r, id);
				if (res != null) {
					break;
				}
			}
		}

		return res;
	}

	@Override
	public String getType() {

		return null;
	}
}
