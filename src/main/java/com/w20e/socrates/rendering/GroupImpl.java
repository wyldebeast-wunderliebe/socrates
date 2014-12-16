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
	private String hint = "";

	/**
	 * Hold label for this item.
	 */
	private Label label = Label.EMPTY;

	private String layout = "flow";

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
	public final void setHint(final String newHint) {

		this.hint = newHint;
	}

	/**
	 * @return Returns the label.
	 */
	@Override
	public final Label getLabel() {

		return this.label;
	}

	/**
	 * @param newLabel
	 *            The hint to set.
	 */
	public final void setLabel(final Label newLabel) {

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
