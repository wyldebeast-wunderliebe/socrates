package com.w20e.socrates.rendering;

import java.util.Properties;

/**
 * RenderableImpl provides a basic inplementation for anything that can be used
 * for the rendering framework.
 * @author dokter
 */
public abstract class RenderableImpl extends Properties implements Renderable {

	/**
     * Default serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
	 * Item's label. Default is the empty string.
	 */
	private String label = "";

	/**
	 * Hold the item's hint. Default is set to the empty string.
	 */
	private String hint = "";

	/**
	 * Hold unique id for this item.
	 */
	private String id;

	/**
	 * Constructor for Renderable item. An id should always be there.
	 * @param newId
	 */
	public RenderableImpl(String newId) {

		this.id = newId;
	}

	/**
	 * Return the item's id.
	 */
	@Override
	public String getId() {

		return this.id;
	}

	/**
	 * Return this item's hint.
	 */
	public String getHint() {

		return this.hint;
	}

	/**
	 * Return this item's label.
	 */
	public String getLabel() {

		return this.label;
	}

	/**
	 * Set the hint.
	 * @param newHint
	 */
	public void setHint(final String newHint) {

		this.hint = newHint;
	}

	/**
	 * Set the label.
	 * @param newLabel
	 */
	public void setLabel(final String newLabel) {

		this.label = newLabel;
	}
}
