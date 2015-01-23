package com.w20e.socrates.rendering;


public abstract class GroupImpl extends RenderableContainerImpl implements
		Group {

	/**
	 * UID.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor taking unique id.
	 * 
	 * @param newId
	 */
	public GroupImpl(String newId) {
		super(newId);
	}

	/**
	 * Hold hint for this item.
	 */
	private Translatable hint = TranslatableImpl.EMPTY;

	/**
	 * Hold label for this item.
	 */
	private Translatable label = TranslatableImpl.EMPTY;

	private String layout = "flow";

	/**
	 * @return Returns the hint.
	 */
	@Override
	public final Translatable getHint() {

		return this.hint;
	}

	/**
	 * @param newHint
	 *            The hint to set.
	 */
	public final void setHint(final Translatable newHint) {

		this.hint = newHint;
	}

	/**
	 * @return Returns the label.
	 */
	@Override
	public final Translatable getLabel() {

		return this.label;
	}

	/**
	 * @param newLabel
	 *            The hint to set.
	 */
	public final void setLabel(final Translatable newLabel) {

		this.label = newLabel;
	}

	public String getLayout() {

		return this.layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName().toLowerCase();
	}
}
