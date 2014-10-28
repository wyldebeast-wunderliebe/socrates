package com.w20e.socrates.rendering;

import java.util.ArrayList;
import java.util.List;

public abstract class GroupImpl 
extends RenderableImpl
implements Group {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds a list of items for this group.
	 */
	private List<Renderable> items = new ArrayList<Renderable>();


	/**
	 * Constructor taking unique id.
	 * @param newId
	 */
	public GroupImpl(String newId) {
		super(newId);
	}

	/**
	 * @return Returns the items contained by this group, in the order they were
	 *         added.
	 */
	@Override
	public List<Renderable> getItems() {

		return this.items;
	}

	/**
	 * Add an item to the config.
	 * 
	 * @param item
	 *            The item to add.
	 */
	public final void addItem(final Renderable item) {

		this.items.add(item);
	}

	/**
	 * Hold hint for this item.
	 */
	private String hint = "";

	/**
	 * Hold label for this item.
	 */
	private String label = "";

	/**
	 * @return Returns the hint.
	 */
	@Override
	public final String getHint() {

		return this.hint;
	}

	/**
	 * @param newHint
	 *            The hint to set.
	 */
	@Override
	public final void setHint(final String newHint) {

		this.hint = newHint;
	}

	/**
	 * @return Returns the label.
	 */
	@Override
	public final String getLabel() {

		return this.label;
	}

	/**
	 * @param newLabel
	 *            The hint to set.
	 */
	@Override
	public final void setLabel(final String newLabel) {

		this.label = newLabel;
	}

	/**
	 * Get the item by it's index.
	 * @param pos
	 * @return
	 */
	public Renderable getItem(int pos) {

		return this.items.get(pos);
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName().toLowerCase();
	}
}
