package com.w20e.socrates.rendering;

import java.util.HashMap;
import java.util.Map;


public class RenderConfigImpl extends RenderableContainerImpl
implements RenderConfig, RenderableContainer {

	// Allow for global option lists
    private Map<String, OptionList> optionlists;

	public RenderConfigImpl() {
		super();
        this.optionlists = new HashMap<String, OptionList>();
	}

	public RenderConfigImpl(String id) {
		super(id);
        this.optionlists = new HashMap<String, OptionList>();
	}

	/**
	 * Default uid
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Override
	public String getType() {

		return "layout";
	}
	
	public void addOptionList(OptionList options) {
		
        this.optionlists.put(options.getId(), options);
	}
	
	public OptionList getOptionList(String ref) {

    	return this.optionlists.get(ref);
    }
}
