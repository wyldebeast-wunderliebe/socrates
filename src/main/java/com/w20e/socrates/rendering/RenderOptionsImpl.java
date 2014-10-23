package com.w20e.socrates.rendering;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Implement rendering options, providing a series of enable/disable settings.
 * @author dokter
 *
 */
public class RenderOptionsImpl implements RenderOptions {

	
	private Map<String, Object> options;
	
	
	public RenderOptionsImpl() {
		
		this.options = new HashMap<String, Object>();
	}

	
	public RenderOptionsImpl(Map<String, Object> defaults) {
		
		this.options = defaults;
	}

	
	/**
	 * Enable given option.
	 * @param opt
	 */
	public final void enable(final String opt) {
		
		this.options.put(opt, Boolean.TRUE);
	}


	/**
	 * Disable given option.
	 * @param opt
	 */
	public final void disable(final String opt) {
		
		this.options.put(opt, Boolean.FALSE);
	}


	@Override
	public final boolean isEnabled(final String opt, final boolean defaultValue) {
		
		if (this.options.containsKey(opt)) {
			return Boolean.valueOf(this.options.get(opt).toString()).booleanValue();
		} else {
			return defaultValue;
		}
	}

	@Override
	public final boolean isEnabled(final String opt) {
		
		return isEnabled(opt, false);
	}

	@Override
	public boolean isDisabled(String opt, final boolean defaultValue) {

		if (this.options.containsKey(opt)) {
			return !Boolean.valueOf(this.options.get(opt).toString()).booleanValue();
		} else {
			return defaultValue;
		}		
	}

	@Override
	public final boolean isDisabled(final String opt) {
		
		return isDisabled(opt, false);
	}

	@Override
	public Set<String> keys() {

		return this.options.keySet();
	}
	

	public Object get(String opt) {
		
		return this.options.get(opt);
	}

	/**
	 * Get property, using default value if not available.
	 */
	public Object get(String opt, Object defaultValue) {
		
		if (this.options.get(opt) != null) {
			return this.options.get(opt);
		} else {
			return defaultValue;
		}
	}
}
