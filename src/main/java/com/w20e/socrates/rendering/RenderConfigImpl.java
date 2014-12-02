package com.w20e.socrates.rendering;


public class RenderConfigImpl extends RenderableContainerImpl
implements RenderConfig, RenderableContainer {

	public RenderConfigImpl() {
		super();
	}

	public RenderConfigImpl(String id) {
		super(id);
	}

	/**
	 * Default uid
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getType() {

		return "layout";
	}
}
